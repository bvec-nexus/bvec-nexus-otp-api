package com.bvec.otp.utils;


import org.springframework.stereotype.Component;

@Component
public class GenerateOtp {
    public String getOtp(){

        int otp = (int) ((Math.random() * 1000000));

        return Integer.toString(otp);
    }
}
