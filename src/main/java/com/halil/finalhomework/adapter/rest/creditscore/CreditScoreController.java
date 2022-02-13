package com.halil.finalhomework.adapter.rest.creditscore;

import com.halil.finalhomework.domain.creditscore.CreditScore;
import com.halil.finalhomework.domain.creditscore.CreditScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreditScoreController {

    private final CreditScoreService creditScoreService;

    @PostMapping("/creditscore")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCreditScore(@RequestBody CreditScoreCreateRequest request){
        creditScoreService.createCreditScore(request.convert());
    }

}
