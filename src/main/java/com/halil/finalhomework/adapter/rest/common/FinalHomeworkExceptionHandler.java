package com.halil.finalhomework.adapter.rest.common;

import com.halil.finalhomework.domain.exception.FinalHomeworkDataNotFoundException;
import com.halil.finalhomework.domain.exception.FinalHomeworkValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class FinalHomeworkExceptionHandler {

    @ExceptionHandler(FinalHomeworkDataNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  // isteği işleyemedim
    public ExceptionResponse handleDataNotFoundException(FinalHomeworkDataNotFoundException exception){
        return new ExceptionResponse(exception.getExceptionType());
    }

    @ExceptionHandler(FinalHomeworkValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleValidationException(FinalHomeworkValidationException exception){
        return new ExceptionResponse(exception.getExceptionType());
    }

    /*
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException(Exception e){
        return new ExceptionResponse(ExceptionType.GENERIC_EXCEPTION);
    } */


}
