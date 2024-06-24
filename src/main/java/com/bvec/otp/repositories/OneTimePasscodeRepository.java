package com.bvec.otp.repositories;

import com.bvec.otp.entities.OneTimePasscode;
import com.bvec.otp.entities.OneTimePasscode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OneTimePasscodeRepository extends JpaRepository<OneTimePasscode, String> {
    OneTimePasscode getByEmail(String email);
}
