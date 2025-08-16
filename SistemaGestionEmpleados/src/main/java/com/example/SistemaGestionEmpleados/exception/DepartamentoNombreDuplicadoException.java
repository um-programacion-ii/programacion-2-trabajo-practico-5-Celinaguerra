package com.example.SistemaGestionEmpleados.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DepartamentoNombreDuplicadoException extends RuntimeException {
    public DepartamentoNombreDuplicadoException(String message) {
        super(message);
    }
}
