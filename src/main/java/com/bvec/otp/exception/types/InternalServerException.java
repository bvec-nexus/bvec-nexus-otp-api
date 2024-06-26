package com.bvec.otp.exception.types;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InternalServerException extends RuntimeException{
    private String errorMessage;
    private int errorCode;
    private String errorDetails;

    public InternalServerException(String errorMessage, int errorCode, String errorDetails) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }
}
