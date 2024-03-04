package com.softexpert.food.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValorDecimalNegativoException extends RuntimeException {

    public ValorDecimalNegativoException(String message) {
        super(message);
    }

}
