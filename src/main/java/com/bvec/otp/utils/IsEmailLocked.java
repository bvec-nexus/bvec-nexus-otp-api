package com.bvec.otp.utils;

import com.bvec.otp.entities.OneTimePasscode;
import com.bvec.otp.repositories.OneTimePasscodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class IsEmailLocked {

    @Autowired
    private OneTimePasscodeRepository oneTimePasscodeRepository;

    public boolean isEmailLockedOut(String userId){

        Instant instant = Instant.now();

        Optional<OneTimePasscode> byId = oneTimePasscodeRepository.findById(userId);

        if(byId.get().getLockExpiryTimestamp().before(Date.from(instant))){
            return true;
        }

        return false;
    }

    public boolean isOtpExpired(String userId){
        Instant instant = Instant.now();
        return false;
    }
}
