package com.henrique.login.springbooot.util.validate;

import com.henrique.login.springbooot.model.UserModel;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class IsPasswordExpired {

    public static boolean isPasswordExpired(UserModel user) {
        LocalDate passwordExpiresNow = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
        return user.getPasswordExpires() == null || passwordExpiresNow.isAfter(user.getPasswordExpires());
    }
}
