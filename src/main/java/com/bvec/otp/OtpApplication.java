package com.bvec.otp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtpApplication.class, args);
	}

}
