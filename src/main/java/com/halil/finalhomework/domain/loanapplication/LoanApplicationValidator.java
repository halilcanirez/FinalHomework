package com.halil.finalhomework.domain.loanapplication;

import com.halil.finalhomework.domain.exception.ExceptionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

import static com.halil.finalhomework.domain.loanapplication.LoanApplicationValidator.*;


public interface LoanApplicationValidator extends Function<LoanApplication, ValidationResult> {

    static LoanApplicationValidator isAdult(){
        return loanApplication-> Period.between(loanApplication.getBirthDate(), LocalDate.now()).getYears()>18 ?
                ValidationResult.SUCCESS:ValidationResult.IS_NOT_AN_ADULT;
    }

    static LoanApplicationValidator isPhoneNumberAcceptable(){
        return loanApplication-> loanApplication.getPhoneNumber().length()==10 ?
                ValidationResult.SUCCESS:ValidationResult.PHONE_NUMBER_SIZE_NOT_EQUAL_11;
    }

    default LoanApplicationValidator and (LoanApplicationValidator other){

        return loanApplication -> {
            ValidationResult result = this.apply(loanApplication);
            return result.equals(ValidationResult.SUCCESS) ? other.apply(loanApplication) : result;
        };
    }

    @Getter
    @RequiredArgsConstructor
    enum ValidationResult{
        SUCCESS(null),
        IS_NOT_AN_ADULT(ExceptionType.IS_NOT_AN_ADULT),
        PHONE_NUMBER_SIZE_NOT_EQUAL_11(ExceptionType.PHONE_NUMBER_SIZE_NOT_EQUAL_11);

        private final ExceptionType exceptionType;
    }
}
