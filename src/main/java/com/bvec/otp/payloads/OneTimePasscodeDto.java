package com.bvec.otp.payloads;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class OneTimePasscodeDto {

    private String offerId;
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
