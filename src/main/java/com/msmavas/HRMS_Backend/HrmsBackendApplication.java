package com.msmavas.HRMS_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

@EntityScan(basePackages = "com.msmavas.HRMS_Backend.models")
@EnableJpaRepositories(basePackages = "com.msmavas.HRMS_Backend.repositories")
public class HrmsBackendApplication {
	private static boolean accountCreationInProgress = false;

    public static synchronized boolean isAccountCreationInProgress() {
        return accountCreationInProgress;
    }

    public static synchronized void setAccountCreationInProgress(boolean value) {
        accountCreationInProgress = value;
    }

    public static void main(String[] args) {
        SpringApplication.run(HrmsBackendApplication.class, args);
    }
}
