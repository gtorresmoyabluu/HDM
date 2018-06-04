/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.enums;

/**
 *
 * @author Admin
 */
public enum TextTypeEnm {
    
    ok,         // El mensaje a mostrar cuando un diagnostico o una accion ha terminado correctamente.
    error,      // El mensaje a mostrar cuando un diagnostico o una accion ha terminado incorrectamente.
    detail,     // Texto con el detalle del diagnostico o de la accion
    help,       // El mensaje con informacion adicional de la accion.
    warning,    // El mensaje de alerta a mostrar antes de ejecutar una accion.
    url,        // URL para acciones de tipo deeplink
    header,     // Texto de cabecera
    footer      // Texto al pie
    
}
