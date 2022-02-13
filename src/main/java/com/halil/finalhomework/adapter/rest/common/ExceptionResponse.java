package com.halil.finalhomework.adapter.rest.common;


import com.halil.finalhomework.domain.exception.ExceptionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // testler için
public class ExceptionResponse {

    private Integer code;
    private String message;

    public ExceptionResponse(ExceptionType exceptionType){
        this.code=exceptionType.getCode();
        this.message=exceptionType.getMessage();
    }
}
