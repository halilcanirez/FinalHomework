package com.halil.finalhomework.adapter.api.smssender;


import com.halil.finalhomework.domain.loanapplication.LoanApplication;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;
import com.halil.finalhomework.domain.port.SmsSenderPort;
import org.springframework.stereotype.Service;

@Service
public class MessageSender implements SmsSenderPort {

    @Override
    public void sendMessage(LoanApplicationResult result, String phoneNumber){
        // send message
    }

}
