package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.UserModel;
import com.henrique.login.springbooot.model.dto.PreLoginDTO;
import com.henrique.login.springbooot.repository.UserRepository;
import com.henrique.login.springbooot.util.Constants.ConstantsPreLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Arrays;


@Service
public class PreLoginService {

    private final UserRepository userRepository;

    @Autowired
    public PreLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PreLoginDTO getPreLoginService(LoginModel login) {

        UserModel user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(login.getEmail()))
                .findAny()
                .orElse(null);
        PreLoginDTO preLoginDTO = new PreLoginDTO();

        if (user == null) {
            preLoginDTO.setUser_exists(false);
            preLoginDTO.setPassword_expired(false);
            preLoginDTO.setRequires_mfa(false);
            preLoginDTO.setSso_available(Arrays.asList(""));
            preLoginDTO.setMessage("User does not exist");
            return preLoginDTO;
        }

        LocalDate passwordExpiresNow = LocalDate.from(OffsetDateTime.now().toInstant());

        boolean isPasswordExpired = user.getPasswordExpires() == null || passwordExpiresNow.isAfter(user.getPasswordExpires());

        preLoginDTO.setUser_exists(true);
        preLoginDTO.setPassword_expired(isPasswordExpired);
        preLoginDTO.setRequires_mfa(user.isRequiresMfa());
        preLoginDTO.setSso_available(Arrays.asList("google", "facebook", "twitter"));

        if (isPasswordExpired) {
            preLoginDTO.setMessage(ConstantsPreLogin.PASSWORD_EXPIRED);
        } else if (user.isRequiresMfa()) {
            preLoginDTO.setMessage(ConstantsPreLogin.USER_EXISTS_AND_MULTIFACTOR_MANDATORY);
        } else {
            preLoginDTO.setMessage("User exists and no MFA required");
        }

        return preLoginDTO;
    }
}
