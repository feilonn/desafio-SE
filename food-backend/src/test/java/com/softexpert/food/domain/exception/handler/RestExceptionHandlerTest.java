package com.softexpert.food.domain.exception.handler;

import com.softexpert.food.domain.exception.BadRequestException;
import com.softexpert.food.domain.exception.BadRequestExceptionDetails;
import com.softexpert.food.domain.exception.ValidationExceptionDetails;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class RestExceptionHandlerTest {

    @Test
    public void testHandlerValorDecimalNegativoExceptionDetails() {
        BadRequestException exception = new BadRequestException("Valor decimal negativo não permitido");

        RestExceptionHandler handler = new RestExceptionHandler();

        ResponseEntity<BadRequestExceptionDetails> responseEntity = handler
                .handlerValorDecimalNegativoExceptionDetails(exception);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getTimeStamp()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(responseEntity.getBody().getTitle()).isEqualTo("Bad Request");
        assertThat(responseEntity.getBody().getErrorMessage()).isEqualTo("Valor decimal" +
                " negativo não permitido");
    }

}