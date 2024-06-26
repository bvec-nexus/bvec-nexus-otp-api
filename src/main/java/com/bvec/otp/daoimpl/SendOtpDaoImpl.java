package com.bvec.otp.daoimpl;


import com.bvec.otp.dao.SendOtpDao;
import com.bvec.otp.entities.OneTimePasscode;
import com.bvec.otp.repositories.OneTimePasscodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendOtpDaoImpl implements SendOtpDao {

    private static final Logger logger = LoggerFactory.getLogger(SendOtpDaoImpl.class);

    @Autowired
    private OneTimePasscodeRepository oneTimePasscodeRepository;

    @Override
    public OneTimePasscode getEmailByUpdatedTimestamp(String email) {

        logger.info("getEmailByUpdatedTimestamp || Entry");

        List<OneTimePasscode> byEmailOrderByUpdatedTimestampDesc
                = oneTimePasscodeRepository.findByEmailOrderByUpdatedTimestampDesc(email);
        if(byEmailOrderByUpdatedTimestampDesc.isEmpty()){
            logger.info("getEmailByUpdatedTimestamp || Exit");
            return null;
        }else{
            logger.info("getEmailByUpdatedTimestamp || Exit");
            return byEmailOrderByUpdatedTimestampDesc.get(0);
        }

    }

    @Override
    public OneTimePasscode saveOtpDetails(OneTimePasscode oneTimePasscode) {
        logger.info("saveOtpDetails || Entry");
        return oneTimePasscodeRepository.save(oneTimePasscode);
    }
}