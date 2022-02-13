package com.halil.finalhomework.adapter.rest.loanapplication;


import com.halil.finalhomework.domain.loanapplication.LoanApplication;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationCreateRequest {

    @NotNull
    private Long    identityNumber;
    @NotBlank
    private String  name;
    @NotBlank
    private String  surname;
    @NotBlank
    private String  phoneNumber;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private Integer salary;

    public LoanApplication convertToLoanApplication(){
        return LoanApplication.builder()
                .identityNumber(identityNumber)
                .name(name)
                .surname(surname)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .salary(salary)
                .build();
    }
}
