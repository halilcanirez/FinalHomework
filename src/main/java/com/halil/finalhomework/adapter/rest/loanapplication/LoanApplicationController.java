package com.halil.finalhomework.adapter.rest.loanapplication;

import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    @PostMapping("/loan-application")
    @ResponseStatus(HttpStatus.CREATED)
    public LoanApplicationResponse createLoanApplication(@RequestBody @Valid LoanApplicationCreateRequest request){
        LoanApplicationResult applicationResult = loanApplicationService.createLoanApplication(request.convertToLoanApplication());
        return LoanApplicationResponse.convertToLoanApplicationResponse(applicationResult);
    }

    @GetMapping("/loan-application")
    @ResponseStatus(HttpStatus.OK)
    public List<LoanApplicationResponse> retrieveLoanApplications(@RequestParam Long identityNumber){
        return loanApplicationService.retrieveLoanApplications(identityNumber).stream()
                .map(LoanApplicationResponse::convertToLoanApplicationResponse)
                .collect(Collectors.toList());
    }
}
