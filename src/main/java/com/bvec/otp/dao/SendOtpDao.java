package com.bvec.otp.dao;

import com.bvec.otp.payloads.OtpDto;

public interface SendOtpDao {
    public String setOtpDetails(OtpDto otpDto);
}
