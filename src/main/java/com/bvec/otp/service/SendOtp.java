package com.bvec.otp.service;

import com.bvec.otp.payloads.OtpDto;

public interface SendOtp {
    String sendOtp(OtpDto otpDto);
}
