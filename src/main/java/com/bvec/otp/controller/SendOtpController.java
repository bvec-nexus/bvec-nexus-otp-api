package com.bvec.otp.controller;

import com.bvec.otp.payloads.OtpDto;
import com.bvec.otp.serviceimpl.SendOtpImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendOtpController {

    private static final Logger logger = LoggerFactory.getLogger(SendOtpController.class);

    @Autowired
    private SendOtpImpl serviceOtp;

    @PostMapping("/sendOtp")
    public ResponseEntity<String> sendOtp(@Valid @RequestBody OtpDto otpDto){

        logger.info("SendOtpController || Entry");

        String otp = serviceOtp.sendOtp(otpDto);

        logger.info("SendOtpController || Exit");
        return new ResponseEntity<>(otp, HttpStatus.CREATED);
    }
}
