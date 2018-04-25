package com.bluu.hdm.web.progressbar;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class ProgressBar implements Serializable{

    public static Integer MAX_PROGRESS = 99;

    // Identificador del progressbar
    private String id;

    // Listener para comunicar el avance y la finalización
    private ProgressBarListener listener;

    // Retraso a esperar
    private long delay;

    // Progreso
    private int progress;

    // Parámetros
    private int maxTime;
    private int expectedTime;
    private int expectedProgress;

    // Flags
    private boolean showExpected;
    private boolean expectedShowed;
    private boolean showMax;
    private boolean finishExecuted;

    // Other
    private long startTT;
    private int maxTimeMillis;
    private int expectedTimeMillis;

    private long finishTT;
    private int finishTimeMillis;
    private int finishProgress;

    // Lock
    private final transient Object lock = new Object();

    public ProgressBar(ProgressBarListener listener, int maxTime, int expectedTime, int expectedProgress) {
        this.id = UUID.randomUUID().toString();
        this.listener = listener;
        this.maxTime = maxTime;
        this.expectedTime = expectedTime;
        this.expectedProgress = expectedProgress;

        // Trasformación en milisegundos
        maxTimeMillis = maxTime != -1 ? maxTime * 1000 : -1;
        expectedTimeMillis = expectedTime != -1 ? expectedTime * 1000 : -1;
    }

    public String getId() {
        return id;
    }

    public int getProgress() {
        return progress;
    }

    private long getRandomDelay(double min, double max) {
        Random r = new Random();
        double seconds = (r.nextInt((int) ((max - min) * 10 + 1)) + min * 10) / 10.0;
        return (long) (seconds * 1000);
    }

    private void sendStepSignal() {
        if (listener != null)
            listener.onProgressBarUpdated(id, progress, false, false);
    }

    private void sendCompletedSignal() {
        if (listener != null)
            listener.onProgressBarUpdated(id, progress, true, finishExecuted);
    }

    public void doStart() {
        if (maxTime <= 0 || expectedTime <= 0 || maxTime <= expectedTime
                || expectedProgress <= 0 || expectedProgress >= MAX_PROGRESS)
            throw new ExceptionInInitializerError("No required values assigned or out of range");


        // Se inicializa la barra de progreso
        progress = 0;

        // Se envía la señal de progreso al listener
        sendStepSignal();

        // Se inicializa flags
        showExpected = false;
        expectedShowed = false;
        showMax = false;
        finishExecuted = false;

        // Timestamp de comienzo
        startTT = new Date().getTime();

        // Se calcula el primer delay
        delay = getRandomDelay(0.2, 0.8);

        // Se lanza el hilo de ejecución
        ProgressBarThread thread = new ProgressBarThread();
        thread.setDaemon(true);
        thread.start();
    }


    public void doFinish(int seconds) {
        if (seconds < 0)
            throw new ExceptionInInitializerError("Finish timeout is out of range");

        synchronized (lock) {
            // Se asigna el tiempo en millis que ha de pasar hasta la finalización completa
            finishTimeMillis = seconds * 1000;

            // Se asigna el timestamp en el que comienza la finalización
            finishTT = new Date().getTime();

            // Se asigna el porcentaje del progress que queda por rellenar
            finishProgress = getProgress();

            // Se asigna el flag de que la finalización ha sido solicitada
            finishExecuted = true;
        }
    }

    private class ProgressBarThread extends Thread {

        @Override
        public void run() {
            // Esperamos el delay
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignore) {
            }

            // Comprobamos si el se ha invocado la finalización
            boolean finish;
            synchronized (lock) {
                finish = finishExecuted;
            }

            // Inicializamos el siguiente delay
            delay = -1;

            // Realizamos el proceso en función de se se ha pulsado la finalización o no
            if (!finish) {
                // Se calcula el tiempo trascurrido desde el comienzo
                long now = new Date().getTime();
                long elapsedTime = now - startTT;

                if (showExpected) {
                    /***************************************************
                     Se ha indicado que se muestre el total del esperado
                     ***************************************************/

                    // Limpieza de flag
                    showExpected = false;

                    // Asignación de flag
                    expectedShowed = true;

                    // Se actualiza el progreso con el porcentaje esperado
                    progress = expectedProgress;

                    // Se calcula el nuevo delay
                    delay = getRandomDelay(0.2, 0.8);

                    // Se envía la señal de progreso al listener
                    sendStepSignal();

                } else if (showMax) {
                    /***************************************************
                     Se ha indicado que se muestre el total de la barra
                     ***************************************************/

                    // Limpieza de flag
                    showMax = false;

                    // Se actualiza el progreso con el porcentaje máximo
                    progress = MAX_PROGRESS;

                    // Se envía la señal de finalización al listener
                    sendCompletedSignal();
                } else if (!expectedShowed) {
                    /***************************************************
                     No se ha rellenado la zona esperada
                     ***************************************************/

                    if (elapsedTime < expectedTimeMillis) {
                        // Calculamos el progreso para la parte esperada
                        progress = (int) (expectedProgress * elapsedTime) / expectedTimeMillis;

                        // Calculamos el nuevo delay
                        delay = getRandomDelay(0.2, 0.8);

                        // Si no había llegado el tiempo esperado y con el nuevo retraso se supera, cambiamos
                        // el retraso para que coincida la ejecución con el tiempo esperado, e indicamos
                        // que rellene el porcentaje esperado dicha próxima vez
                        if (now + delay >= startTT + expectedTimeMillis) {
                            showExpected = true;
                            delay = startTT + expectedTimeMillis - now;
                        }
                    } else {
                        // Se actualiza el progreso con el porcentaje esperado
                        progress = expectedProgress;

                        // Asignación de flag
                        expectedShowed = true;

                        // Calculo del nuevo delay
                        delay = getRandomDelay(0.2, 0.8);
                    }

                    // Se envía la señal de progreso al listener
                    sendStepSignal();

                } else {
                    /***************************************************
                     Está en la zona final
                     ***************************************************/

                    if (elapsedTime < maxTimeMillis) {
                        // Calculamos el progreso para el resto del progress
                        progress = expectedProgress + (int) ((MAX_PROGRESS - expectedProgress) * (elapsedTime - expectedTimeMillis) / (maxTimeMillis - expectedTimeMillis));

                        // Calculamos el nuevo delay
                        delay = getRandomDelay(0.2, 0.8);

                        // Si no había llegado el tiempo máximo y con el nuevo retraso se supera, cambiamos
                        // el restraso para que coincida la ejecución con el tiempo máximo, e indicamos
                        // que rellene el porcentaje máximo dicha próxima vez
                        if (now + delay >= startTT + maxTimeMillis) {
                            showMax = true;
                            delay = startTT + maxTimeMillis - now;
                        }

                        // Se envía la señal de progreso al listener
                        sendStepSignal();
                    } else {
                        // Se actualiza el progreso con el porcentaje máximo
                        progress = MAX_PROGRESS;

                        // Se envía la señal de finalización al listener
                        sendCompletedSignal();
                    }
                }
            } else {
                // Se calcula el tiempo trascurrido desde la finalización
                long now = new Date().getTime();
                long elapsedTime = now - finishTT;
                if (elapsedTime < finishTimeMillis) {
                    // Calculamos el progreso de finalización
                    progress = finishProgress + (int) ((MAX_PROGRESS - finishProgress) * elapsedTime / finishTimeMillis);

                    // Calculamos el nuevo delay
                    delay = getRandomDelay(0.2, 0.8);

                    // Si no había llegado el tiempo máximo y con el nuevo retraso se supera, cambiamos
                    // el restraso para que coincida la ejecución con el tiempo máximo, e indicamos
                    // que rellene el porcentaje máximo dicha próxima vez
                    if (now + delay >= finishTT + finishTimeMillis) {
                        showMax = true;
                        delay = finishTT + finishTimeMillis - now;
                    }

                    // Se envía la señal de progreso al listener
                    sendStepSignal();
                } else {
                    // Asignación del progreso al maximo
                    progress = MAX_PROGRESS;

                    // Se envía la señal de finalización al listener
                    sendCompletedSignal();
                }
            }


            // Llamada recursiva si la barra no se ha completado
            if (delay != -1) {
                ProgressBarThread thread = new ProgressBarThread();
                thread.setDaemon(true);
                thread.start();
            }
        }
    }

}
