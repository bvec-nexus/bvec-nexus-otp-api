package com.bvec.otp.serviceimpl;

import com.bvec.otp.daoimpl.SendOtpDaoImpl;
import com.bvec.otp.payloads.OtpDto;
import com.bvec.otp.service.SendOtp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SendOtpImpl implements SendOtp {

    private static final Logger logger = LoggerFactory.getLogger(SendOtpImpl.class);

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private SendOtpDaoImpl sendOtpDao;
    @Override
    public String sendOtp(OtpDto otpDto) {

        logger.info("SendOtpImpl || Entry");

        String s = sendOtpDao.setOtpDetails(otpDto);

        logger.info("SendOtpImpl || Exit");

        return s;
    }
}
