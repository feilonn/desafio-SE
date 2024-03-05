package com.softexpert.food.domain.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationExceptionDetailsTest {

    @Test
    public void testGetterAndSuperGetter() {
        LocalDateTime timeStamp = LocalDateTime.now();
        ValidationExceptionDetails validationExceptionDetails = ValidationExceptionDetails
                .builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .campos("campo1")
                .messageCampos("mensagem")
                .build();

        assertThat(validationExceptionDetails.getTitle()).isEqualTo("Error");
        assertThat(validationExceptionDetails.getStatus()).isEqualTo(500);
        assertThat(validationExceptionDetails.getErrorMessage()).isEqualTo("Internal Server Error");
        assertThat(validationExceptionDetails.getTimeStamp()).isEqualTo(timeStamp);
        assertThat(validationExceptionDetails.getCampos()).isEqualTo("campo1");
        assertThat(validationExceptionDetails.getMessageCampos()).isEqualTo("mensagem");
    }

    @Test
    public void testConstrutor() {
        LocalDateTime timeStamp = LocalDateTime.now();
        ValidationExceptionDetails validationExceptionDetails = ValidationExceptionDetails
                .builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .campos("campo1")
                .messageCampos("mensagem")
                .build();

        assertThat(validationExceptionDetails.getTitle()).isEqualTo("Error");
        assertThat(validationExceptionDetails.getStatus()).isEqualTo(500);
        assertThat(validationExceptionDetails.getErrorMessage()).isEqualTo("Internal Server Error");
        assertThat(validationExceptionDetails.getTimeStamp()).isEqualTo(timeStamp);
        assertThat(validationExceptionDetails.getCampos()).isEqualTo("campo1");
        assertThat(validationExceptionDetails.getMessageCampos()).isEqualTo("mensagem");
    }

    @Test
    public void testToString() {
        LocalDateTime timeStamp = LocalDateTime.now();
        ValidationExceptionDetails validationExceptionDetails = ValidationExceptionDetails
                .builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .build();

        assertThat(validationExceptionDetails.toString()).isNotBlank();
    }

    @Test
    public void testEquals() {
        LocalDateTime timeStamp = LocalDateTime.now();
        ValidationExceptionDetails validationExceptionDetails1 = ValidationExceptionDetails
                .builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .campos("campo1")
                .messageCampos("mensagem")
                .build();

        ValidationExceptionDetails validationExceptionDetails2 = ValidationExceptionDetails
                .builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .campos("campo1")
                .messageCampos("mensagem")
                .build();

        assertThat(validationExceptionDetails1).isEqualTo(validationExceptionDetails2);
    }

    @Test
    public void testHashCode() {
        LocalDateTime timeStamp = LocalDateTime.now();
        ValidationExceptionDetails validationExceptionDetails1 = ValidationExceptionDetails
                .builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .campos("campo1")
                .messageCampos("mensagem")
                .build();

        ValidationExceptionDetails validationExceptionDetails2 = ValidationExceptionDetails
                .builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .campos("campo1")
                .messageCampos("mensagem")
                .build();

        assertThat(validationExceptionDetails1.hashCode()).isEqualTo(validationExceptionDetails2.hashCode());
    }
}