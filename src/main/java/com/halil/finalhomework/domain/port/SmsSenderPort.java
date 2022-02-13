package com.halil.finalhomework.domain.port;

import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;

public interface SmsSenderPort {
    void sendMessage(LoanApplicationResult result, String phoneNumber);
}
