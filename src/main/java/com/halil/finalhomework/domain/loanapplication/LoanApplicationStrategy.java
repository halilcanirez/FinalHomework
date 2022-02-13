package com.halil.finalhomework.domain.loanapplication;


import com.halil.finalhomework.domain.loanapplication.applicationrules.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class LoanApplicationStrategy {

    private final LoanApplicationProperties properties;

    private final Map<BiPredicate<Integer,Integer>, Supplier<LoanApplicationRules>> ruleMap = new LinkedHashMap<>() {{
        put((creditScore, salary) -> creditScore < 500, LoanApplicationFirstRule::new);
        put((creditScore, salary) -> creditScore >= 500 && creditScore < 1000 && salary <5000, LoanApplicationSecondRule::new);
        put((creditScore, salary) -> creditScore >= 500 && creditScore < 1000 && salary >=5000, LoanApplicationThirdRule::new);
        put((creditScore, salary) -> creditScore >= 1000,  () -> new LoanApplicationFourthRule());
    }};

    public LoanApplicationResult calculateLoanApplicationResult(LoanApplication loanApplication, Integer creditScore){

        LoanApplicationRules loanApplicationRules= ruleMap.keySet()
                .stream()
                .filter((keyCondition)->keyCondition.test(creditScore, loanApplication.getSalary()))
                .findFirst()
                .map(e -> ruleMap.get(e))
                .get().get();
        return loanApplicationRules.calculateLoanApplicationResult(loanApplication,properties.getCreditLimitMultiplier());
    }


}
