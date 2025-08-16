package com.example.SistemaGestionEmpleados.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProyectoNoEncontradoException extends RuntimeException {
    public ProyectoNoEncontradoException(String message) {
        super(message);
    }
}