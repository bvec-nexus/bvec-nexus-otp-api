package com.bvec.otp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OneTimePasscodeDto {

    private String userId;

    private String encryptedOtp;

    private String email;

    private Date createdTimestamp;

    private Date updatedTimestamp;

    private Date expiryTimestamp;

    private Date lockExpiryTimestamp;

    private Date acceptedTimestamp;

    private int failedAttemptCount;

    private int sendCount;
}
