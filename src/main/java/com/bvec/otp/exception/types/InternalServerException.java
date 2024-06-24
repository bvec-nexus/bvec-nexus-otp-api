package com.bvec.otp.exception.types;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternalServerException extends RuntimeException{
    private String errorMessage;
    private String errorCode;
    private String errorDetails;
}
