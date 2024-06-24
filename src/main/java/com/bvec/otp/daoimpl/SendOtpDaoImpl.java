package com.bvec.otp.daoimpl;

import com.bvec.otp.constant.Constant;
import com.bvec.otp.dao.SendOtpDao;
import com.bvec.otp.entities.OneTimePasscode;
import com.bvec.otp.payloads.OneTimePasscodeDto;
import com.bvec.otp.payloads.OtpDto;
import com.bvec.otp.repositories.OneTimePasscodeRepository;
import com.bvec.otp.utils.GenerateOtp;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class SendOtpDaoImpl implements SendOtpDao {

    private static final Logger logger = LoggerFactory.getLogger(SendOtpDaoImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OneTimePasscodeRepository oneTimePasscodeRepository;

    @Autowired
    private GenerateOtp generateOtp;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private OneTimePasscode dtoToOneTimePasscode(OneTimePasscodeDto oneTimePasscodeDto){
        return modelMapper.map(oneTimePasscodeDto, OneTimePasscode.class);
    }

    private OneTimePasscodeDto oneTimePasscodeToDto(OneTimePasscode oneTimePasscode){
        return modelMapper.map(oneTimePasscode, OneTimePasscodeDto.class);
    }
    @Override
    public String setOtpDetails(OtpDto otpDto) {

        logger.info("SendOtpDaoImpl || Entry");
        Instant instant = Instant.now();
        Date date = Date.from(instant);
        Date expiryTimeStamp = Date.from(instant.plus(5, ChronoUnit.MINUTES));
        Date lockExpiryTimeStamp = Date.from(instant.plus(30, ChronoUnit.MINUTES));

//        List<OneTimePasscode> byEmail = oneTimePasscodeRepository.getByEmail(otpDto.getEmail());
//        List<OneTimePasscode> updatedTimestamp = oneTimePasscodeRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedTimestamp"));
//        OneTimePasscode data = updatedTimestamp.get(0);
//

        OneTimePasscode byEmail = oneTimePasscodeRepository.getByEmail(otpDto.getEmail());

        String userId = otpDto.getEmail()+date.toString();

        if(byEmail==null){
            OneTimePasscodeDto details = new OneTimePasscodeDto();

            String otp = generateOtp.getOtp();

            details.setEmail(otpDto.getEmail());
            details.setCreatedTimestamp(date);
            details.setUserId(passwordEncoder.encode(userId));
            details.setExpiryTimestamp(expiryTimeStamp);
            details.setUpdatedTimestamp(date);
            details.setEncryptedOtp(passwordEncoder.encode(otp));
            details.setSendCount(1);
            details.setFailedAttemptCount(0);
            details.setLockExpiryTimestamp(null);

            oneTimePasscodeRepository.save(dtoToOneTimePasscode(details));

            logger.info("SendOtpDaoImpl || Entry");

            return otp +" userId "+userId;
        }

        return null;
    }
}
