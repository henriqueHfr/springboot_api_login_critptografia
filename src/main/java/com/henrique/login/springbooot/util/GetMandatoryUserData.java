package com.henrique.login.springbooot.util;

import org.slf4j.Logger;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

public class GetMandatoryUserData {

    private Logger log;

    public String getPasswordExpires(){
        log.info("Getting password expiration date");
        OffsetDateTime password_expires = OffsetDateTime.now().plusDays(30);
        return password_expires.toString();
    }

    public List<String> getSSOAvailable() {
        log.info("Getting SSO available");
        return Arrays.asList("Google", "Facebook", "Twitter");
    }
}
