package com.bvec.otp.utils;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class CommonUtility {

    public static String generateOtp(){

        int otp = (int) (Math.random() * 1000000);

        return Integer.toString(otp);
    }

    public static boolean isEmailLockedOut(Date getLockExpiryTimestamp){

        Instant instant = Instant.now();

        if (Objects.isNull(getLockExpiryTimestamp)){
            return false;
        }

        if(getLockExpiryTimestamp.before(Date.from(instant))){
            return true;
        }

        return false;
    }

    public static boolean isOtpExpired(Date getExpiryTimestamp){
        Instant instant = Instant.now();
        Date date = Date.from(instant);


        if(getExpiryTimestamp.before(date)){
            return true;
        }
        return false;
    }
}
