package com.halil.finalhomework.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    MEMBER_DATA_NOT_FOUND(1001, "MEMBER NOT FOUND"),
    CREDIT_SCORE_DATA_NOT_FOUND(1002, "Member's Credit Score Not Found"),

    IS_NOT_AN_ADULT(2001, "NOT ADULT"),
    PHONE_NUMBER_SIZE_NOT_EQUAL_11(2002 , "PHONE NUMBER IS NOT EQUAL TO 11");


    private final Integer code;
    private final String message;
}
