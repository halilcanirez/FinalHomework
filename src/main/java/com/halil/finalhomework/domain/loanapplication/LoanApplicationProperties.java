package com.halil.finalhomework.domain.loanapplication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "loan-application")
@Validated
public class LoanApplicationProperties {

     @NotNull
     private int creditLimitMultiplier;

}
