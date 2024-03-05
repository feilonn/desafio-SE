package com.softexpert.food.domain.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class BadRequestExceptionDetailsTest {

    @Test
    public void testGetterAndSuperGetter() {
        LocalDateTime timeStamp = LocalDateTime.now();
        BadRequestExceptionDetails badRequestExceptionDetails = BadRequestExceptionDetails
                .builder()
                .title("Bad Request")
                .status(400)
                .errorMessage("Bad Request Error")
                .timeStamp(timeStamp)
                .build();

        assertThat(badRequestExceptionDetails.getTitle()).isEqualTo("Bad Request");
        assertThat(badRequestExceptionDetails.getStatus()).isEqualTo(400);
        assertThat(badRequestExceptionDetails.getErrorMessage()).isEqualTo("Bad Request Error");
        assertThat(badRequestExceptionDetails.getTimeStamp()).isEqualTo(timeStamp);
    }

    @Test
    public void testConstrutor() {
        LocalDateTime timeStamp = LocalDateTime.now();
        BadRequestExceptionDetails badRequestExceptionDetails = BadRequestExceptionDetails
                .builder()
                .title("Bad Request")
                .status(400)
                .errorMessage("Bad Request Error")
                .timeStamp(timeStamp)
                .build();

        assertThat(badRequestExceptionDetails.getTitle()).isEqualTo("Bad Request");
        assertThat(badRequestExceptionDetails.getStatus()).isEqualTo(400);
        assertThat(badRequestExceptionDetails.getErrorMessage()).isEqualTo("Bad Request Error");
        assertThat(badRequestExceptionDetails.getTimeStamp()).isEqualTo(timeStamp);
    }

    @Test
    public void testToString() {
        LocalDateTime timeStamp = LocalDateTime.now();
        BadRequestExceptionDetails badRequestExceptionDetails = BadRequestExceptionDetails
                .builder()
                .title("Bad Request")
                .status(400)
                .errorMessage("Bad Request Error")
                .timeStamp(timeStamp)
                .build();

        assertThat(badRequestExceptionDetails.toString()).isNotBlank();
    }

    @Test
    public void testEquals() {
        LocalDateTime timeStamp = LocalDateTime.now();
        BadRequestExceptionDetails badRequestExceptionDetails1 = BadRequestExceptionDetails
                .builder()
                .title("Bad Request")
                .status(400)
                .errorMessage("Bad Request Error")
                .timeStamp(timeStamp)
                .build();

        BadRequestExceptionDetails badRequestExceptionDetails2 = BadRequestExceptionDetails
                .builder()
                .title("Bad Request")
                .status(400)
                .errorMessage("Bad Request Error")
                .timeStamp(timeStamp)
                .build();

        assertThat(badRequestExceptionDetails1).isEqualTo(badRequestExceptionDetails2);
    }

    @Test
    public void testHashCode() {
        LocalDateTime timeStamp = LocalDateTime.now();
        BadRequestExceptionDetails badRequestExceptionDetails1 = BadRequestExceptionDetails
                .builder()
                .title("Bad Request")
                .status(400)
                .errorMessage("Bad Request Error")
                .timeStamp(timeStamp)
                .build();

        BadRequestExceptionDetails badRequestExceptionDetails2 = BadRequestExceptionDetails
                .builder()
                .title("Bad Request")
                .status(400)
                .errorMessage("Bad Request Error")
                .timeStamp(timeStamp)
                .build();

        assertThat(badRequestExceptionDetails1.hashCode()).isEqualTo(badRequestExceptionDetails2.hashCode());
    }
}