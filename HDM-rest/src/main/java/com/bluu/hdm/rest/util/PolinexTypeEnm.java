package com.bluu.hdm.rest.util;

public enum PolinexTypeEnm {

    EXGLOBAL("polinex.type.exglobal"),
    EXESPEC("polinex.type.exespec"),
    INCL("polinex.type.incl");

    private final String nombre;

    PolinexTypeEnm(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
