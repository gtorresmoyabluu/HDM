/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.enums;

import com.bluu.hdm.rest.vo.LogVO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Marco Valdés
 */
public class ProcessLog {

    private void addLogToStack(
            Stack<LogVO> entries,
            boolean firstLineReaded,
            LogSeverityEnum severity,
            LogSeverityEnum minSeverity,
            String msgFilter,
            String message,
            String timestamp,
            String clazz,
            Long id) {
        if (firstLineReaded && (severity == LogSeverityEnum.FATAL
                || (severity == LogSeverityEnum.ERROR && (minSeverity == LogSeverityEnum.ERROR || minSeverity == LogSeverityEnum.WARN || minSeverity == LogSeverityEnum.INFO))
                || (severity == LogSeverityEnum.WARN && (minSeverity == LogSeverityEnum.WARN || minSeverity == LogSeverityEnum.INFO))
                || (severity == LogSeverityEnum.INFO && minSeverity == LogSeverityEnum.INFO))
                && (StringUtils.isBlank(msgFilter) || message.toLowerCase().contains(msgFilter.toLowerCase()))) {

            // Si la pila es mayor de 300 saco la primera para no tener nunca más de 300 trazas
            if (entries.size() >= 300) {
                entries.remove(0);
            }
            // Añado a la pila la nueva entrada
            entries.push(new LogVO(severity, timestamp, clazz, message, id));
        }
    }

    private List<LogVO> processLogFile(String path, LogSeverityEnum minSeverity, String msgFilter, Integer maxLines) {
        BufferedReader br = null;
        try {
            String line;
            String timestamp = "";
            LogSeverityEnum severity = null;
            String clazz = "";
            String message = "";
            boolean firstLineReaded = false;
            final Stack<LogVO> entries = new Stack<>();
            br = new BufferedReader(new FileReader(new File(path)));
            Integer cont = 0;
            boolean flag = false;
            String severityStr = "";
            while ((line = br.readLine()) != null) {
                final int overscores = StringUtils.countMatches(line, " - ");
                if (overscores >= 3) {
                    // Se guarda la entrada anterior    
                    if (!flag) {
                        addLogToStack(entries, firstLineReaded, severity, minSeverity, msgFilter, message, timestamp, clazz, cont.longValue());
                    }
                    // Se captura la nueva entrada
                    String[] tokens = line.split(" - ");
                    timestamp = tokens[0].trim();
                    severityStr = tokens[1].trim();
                    if (severityStr.equals("%-7p- %t %c %x")) {
                        severity = null;
                        flag = true;
                    }
                    else{
                        severity = LogSeverityEnum.valueOf(severityStr);
                        flag = false;
                    }
                    clazz = tokens[2].trim();
                    if (tokens.length >= 4) {
                        message = tokens[3].trim();
                    } else {
                        message = "";
                    }

                    // Se indica que ya se ha leído la primera línea del fichero
                    if (!firstLineReaded) {
                        firstLineReaded = true;
                    }

                } else {
                    message = message + "\n" + line;
                }
                cont++;
            }

            br.close();

            // Se añade la última línea
            if (firstLineReaded) {
                addLogToStack(entries, firstLineReaded, severity, minSeverity, msgFilter, message, timestamp, clazz, cont.longValue());
            }

            // Se filtran por el número máximo de líneas
            if (maxLines > entries.size()) {
                maxLines = entries.size();
            }

            final List<LogVO> result = new ArrayList<>();
            for (int i = 0; i < maxLines; i++) {
                result.add(entries.pop());
            }

            return result;
        } catch (final IOException ex) {

            throw new RuntimeException(ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (final IOException ex) {

                }
            }
        }
    }

    public List<LogVO> findAllLogEntries(String directory) {//, String fileName) {
        final List<LogVO> result = new ArrayList<>();
        // Obtenemos los filtros
        String msgFilter = null;
        Integer maxLines = 30;
        LogSeverityEnum minSeverity = LogSeverityEnum.INFO;
        // Recupero el listado de ficheros log
        File files = new File(directory);
        // Los ordeno ascendentemente
        Arrays.sort(files.listFiles(), (File f1, File f2) -> Long.valueOf(f2.lastModified()).compareTo(f1.lastModified()));
        for (File f : files.listFiles()) {
            if (FilenameUtils.getExtension(f.getName()).equals("log")) {
                result.addAll(processLogFile(f.getPath(), minSeverity, msgFilter, maxLines));
            }
            if (result.size() >= maxLines) {
                break;
            }
        }
        return result;
    }
}
