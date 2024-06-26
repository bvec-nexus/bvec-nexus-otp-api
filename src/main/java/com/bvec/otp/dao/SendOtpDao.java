package com.bvec.otp.dao;

import com.bvec.otp.entities.OneTimePasscode;
import com.bvec.otp.payloads.OtpDto;

import java.util.List;

public interface SendOtpDao {
    OneTimePasscode getEmailByUpdatedTimestamp(String email);

    OneTimePasscode saveOtpDetails(OneTimePasscode oneTimePasscode);

}
