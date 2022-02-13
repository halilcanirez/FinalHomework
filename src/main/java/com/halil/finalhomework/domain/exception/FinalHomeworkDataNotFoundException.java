package com.halil.finalhomework.domain.exception;

import lombok.Getter;

@Getter
public class FinalHomeworkDataNotFoundException extends RuntimeException{

    private final ExceptionType exceptionType;

    public FinalHomeworkDataNotFoundException( ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
