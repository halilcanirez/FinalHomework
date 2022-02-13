package com.halil.finalhomework.adapter.api.smssender;
import com.halil.finalhomework.domain.loanapplication.LoanApplication;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsSendLoanApplicationRequest extends SmsRequestTemplate {


    SmsSendLoanApplicationRequest convertToSmsSendTemplate(LoanApplicationResult loanApplicationResult , String phoneNumber){
        SmsSendLoanApplicationRequest sendLoanApplicationRequest = new SmsSendLoanApplicationRequest();
        sendLoanApplicationRequest.setMessage("Your application result:" + loanApplicationResult.getStatus());
        sendLoanApplicationRequest.setPhoneNumber(phoneNumber);
        return sendLoanApplicationRequest;
    }

}
