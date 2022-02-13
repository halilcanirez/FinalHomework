package com.halil.finalhomework;

import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationStatus;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LoanApplicationChechkRules {


    public static void main(String[] args) {

        Map<BiPredicate<Integer,Integer>, Supplier<LoanApplicationResult>> ruleMap = new LinkedHashMap<>() {{
            put((creditScore, salary) -> creditScore < 500, () -> calculate());
            put((creditScore, salary) -> creditScore >= 500 && creditScore < 1000 && salary <5000,  () -> calculate2());
            put((creditScore, salary) -> creditScore >= 500 && creditScore < 1000 && salary >5000,  () -> calculate3());
            put((creditScore, salary) -> creditScore >= 1000,  () -> calculate4());
        }};

        LoanApplicationResult result = ruleMap.keySet()
                .stream()
                .filter((keyCondition)->keyCondition.test(650,0))
                .findFirst()
                .map(e -> ruleMap.get(e))
                .get().get();

        System.out.println(result.getStatus() + "  " + result.getLimit());
    }

    public static LoanApplicationResult calculate(){
        LoanApplicationResult loanApplicationResult = new LoanApplicationResult();
        loanApplicationResult.setStatus(LoanApplicationStatus.DECLINED);
        loanApplicationResult.setLimit(0);
        return loanApplicationResult;
    }

    public static LoanApplicationResult calculate2(){
        LoanApplicationResult loanApplicationResult = new LoanApplicationResult();
        loanApplicationResult.setStatus(LoanApplicationStatus.ACCEPTED);
        loanApplicationResult.setLimit(10000);
        return loanApplicationResult;
    }

    public static LoanApplicationResult calculate3(){
        LoanApplicationResult loanApplicationResult = new LoanApplicationResult();
        loanApplicationResult.setStatus(LoanApplicationStatus.ACCEPTED);
        loanApplicationResult.setLimit(10000);
        return loanApplicationResult;
    }
    public static LoanApplicationResult calculate4(){
        LoanApplicationResult loanApplicationResult = new LoanApplicationResult();
        loanApplicationResult.setStatus(LoanApplicationStatus.ACCEPTED);
        loanApplicationResult.setLimit(4);
        return loanApplicationResult;
    }
}
