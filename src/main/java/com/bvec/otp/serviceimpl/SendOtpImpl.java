package com.bvec.otp.serviceimpl;

import com.bvec.otp.dao.SendOtpDao;
import com.bvec.otp.entities.OneTimePasscode;
import com.bvec.otp.exception.types.InternalServerException;
import com.bvec.otp.payloads.OneTimePasscodeDto;
import com.bvec.otp.payloads.OtpDto;
import com.bvec.otp.service.SendOtp;
import com.bvec.otp.utils.CommonUtility;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Service
public class SendOtpImpl implements SendOtp {

    private static final Logger logger = LoggerFactory.getLogger(SendOtpImpl.class);

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SendOtpDao sendOtpDao;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private OneTimePasscode dtoToOneTimePasscode(OneTimePasscodeDto oneTimePasscodeDto){
        return modelMapper.map(oneTimePasscodeDto, OneTimePasscode.class);
    }
    @Override
    public String sendOtp(OtpDto otpDto) {

        logger.info("SendOtpImpl || Entry");

        Instant instant = Instant.now();

        OneTimePasscode byEmail = sendOtpDao.getEmailByUpdatedTimestamp(otpDto.getEmail());
        String offerId = otpDto.getEmail()+instant.toEpochMilli();

        if(Objects.isNull(byEmail)){

            String otp = CommonUtility.generateOtp();

            OneTimePasscodeDto details = setOtpDetails(otp, otpDto.getEmail(),
                    offerId, Date.from(instant), 1, instant);

            sendOtpDao.saveOtpDetails(dtoToOneTimePasscode(details));

            logger.info("SendOtpDaoImpl || Entry");

            return otp +" offerId "+offerId;
        }

        boolean emailLockedOut = CommonUtility.isEmailLockedOut(byEmail.getLockExpiryTimestamp());
        boolean otpExpired = CommonUtility.isOtpExpired( byEmail.getExpiryTimestamp());

        if(emailLockedOut){
            throw new InternalServerException("Otp locked out", 400,
                    "Try after ");
        }


        if(otpExpired){
            String getOtp = CommonUtility.generateOtp();

            OneTimePasscodeDto oneTimePasscodeDto = setOtpDetails(getOtp, otpDto.getEmail(),
                    offerId, Date.from(instant),
                    byEmail.getSendCount()+1, instant);
            sendOtpDao.saveOtpDetails(dtoToOneTimePasscode(oneTimePasscodeDto));

            return "otp expired| new otp: "+getOtp;
        }else {
            return "Otp already sent | offerId " + offerId ;
        }
    }

    private OneTimePasscodeDto setOtpDetails(String otp, String email, String offerId,
                                             Date createdTimestamp,
                                             int sendCount,Instant instant){

        return OneTimePasscodeDto.builder()
                .encryptedOtp(passwordEncoder.encode(otp))
                .email(email)
                .offerId(passwordEncoder.encode(offerId))
                .acceptedTimestamp(null)
                .createdTimestamp(createdTimestamp)
                .expiryTimestamp(Date.from(instant.plus(5, ChronoUnit.MINUTES)))
                .updatedTimestamp(Date.from(instant))
                .lockExpiryTimestamp(null)
                .sendCount(sendCount)
                .failedAttemptCount(0)
                .build();
    }
}

