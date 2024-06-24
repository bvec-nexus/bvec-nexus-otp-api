package com.bvec.otp.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {
    private String error;
    private int errorCode;

    private String errorDetails;
}
