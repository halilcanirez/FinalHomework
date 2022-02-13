package com.halil.finalhomework.domain.exception;

import lombok.Getter;

@Getter
public class FinalHomeworkValidationException extends RuntimeException{

    private final ExceptionType exceptionType;

    public FinalHomeworkValidationException( ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
