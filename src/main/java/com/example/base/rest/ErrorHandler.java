package com.example.base.rest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handle(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handle(IllegalStateException ex) {
        return ResponseEntity.status(PRECONDITION_FAILED).body(ex.getMessage());
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handle(FileUploadException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
