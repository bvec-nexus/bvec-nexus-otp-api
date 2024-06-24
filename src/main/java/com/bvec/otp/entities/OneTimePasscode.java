package com.bvec.otp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OneTimePasscode {

    @Id
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
