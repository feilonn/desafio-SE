package com.softexpert.food.domain.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionDetailsTest {

    @Test
    public void testGetterAndSetter() {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(LocalDateTime.now())
                .build();

        assertThat(exceptionDetails.getTitle()).isEqualTo("Error");
        assertThat(exceptionDetails.getStatus()).isEqualTo(500);
        assertThat(exceptionDetails.getErrorMessage()).isEqualTo("Internal Server Error");
        assertThat(exceptionDetails.getTimeStamp()).isNotNull();
    }

    @Test
    public void testConstrutores() {
        LocalDateTime timeStamp = LocalDateTime.now();
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .build();

        assertThat(exceptionDetails.getTitle()).isEqualTo("Error");
        assertThat(exceptionDetails.getStatus()).isEqualTo(500);
        assertThat(exceptionDetails.getErrorMessage()).isEqualTo("Internal Server Error");
        assertThat(exceptionDetails.getTimeStamp()).isEqualTo(timeStamp);
    }

    @Test
    public void testToString() {
        LocalDateTime timeStamp = LocalDateTime.now();
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .build();

        assertThat(exceptionDetails.toString()).isEqualTo("ExceptionDetails(title=Error, status=500, errorMessage=Internal Server Error, timeStamp=" + timeStamp.toString() + ")");
    }

    @Test
    public void testEquals() {
        LocalDateTime timeStamp = LocalDateTime.now();
        ExceptionDetails exceptionDetails1 = ExceptionDetails.builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .build();

        ExceptionDetails exceptionDetails2 = ExceptionDetails.builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .build();

        assertThat(exceptionDetails1).isEqualTo(exceptionDetails2);
    }

    @Test
    public void testHashCode() {
        LocalDateTime timeStamp = LocalDateTime.now();
        ExceptionDetails exceptionDetails1 = ExceptionDetails.builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .build();

        ExceptionDetails exceptionDetails2 = ExceptionDetails.builder()
                .title("Error")
                .status(500)
                .errorMessage("Internal Server Error")
                .timeStamp(timeStamp)
                .build();

        assertThat(exceptionDetails1.hashCode()).isEqualTo(exceptionDetails2.hashCode());
    }
}