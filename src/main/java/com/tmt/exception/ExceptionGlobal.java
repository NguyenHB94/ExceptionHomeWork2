package com.tmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

@RestControllerAdvice
public class ExceptionGlobal {


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> conflictData(Exception e) {
        ExceptionEntity ex = new ExceptionEntity();

        ex.setMessage(e.getMessage());
        ex.setCode("409");
        ex.setTimestamp(new Date());

        return new ResponseEntity<>(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> methodNotSupported(Exception e) {
        ExceptionEntity ex = new ExceptionEntity();

        ex.setMessage(e.getMessage());
        ex.setCode("405");
        ex.setTimestamp(new Date());

        return new ResponseEntity<>(ex, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> badRequest(Exception e) {
        ExceptionEntity ex = new ExceptionEntity();

        ex.setMessage(e.getMessage());
        ex.setCode("400");
        ex.setTimestamp(new Date());

        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
}
