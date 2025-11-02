package com.example.SistemaGestionEmpleados.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartamentoNoEncontradoException extends RuntimeException {
    public DepartamentoNoEncontradoException(String message) {
        super(message);
    }
}