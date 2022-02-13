package com.halil.finalhomework.adapter.api.smssender;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SmsRequestTemplate {
    @NotBlank
    private String phoneNumber; // destination

    @NotBlank
    private String message;
}
