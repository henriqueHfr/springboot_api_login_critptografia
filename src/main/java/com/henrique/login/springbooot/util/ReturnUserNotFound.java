package com.henrique.login.springbooot.util;

import com.henrique.login.springbooot.model.dto.MfaDTO;
import com.henrique.login.springbooot.model.dto.PreLoginDTO;
import com.henrique.login.springbooot.Constants.ConstantsLogin;
import com.henrique.login.springbooot.Constants.ConstantsPreLogin;
import org.springframework.stereotype.Service;

@Service
public class ReturnUserNotFound {


    private static PreLoginDTO preLoginDTO = new PreLoginDTO();

    public static PreLoginDTO returnUserNotFoundPreLogin() {
        preLoginDTO.setUser_exists(false);
        preLoginDTO.setPassword_expired(false);
        preLoginDTO.setRequires_mfa(false);
        preLoginDTO.setSso_available(false);
        preLoginDTO.setMessage(ConstantsPreLogin.USER_DOES_NOT_EXIST);
        return preLoginDTO;
    }

    public static MfaDTO returnUserNotFoundLogin() {
        MfaDTO loginDTO = new MfaDTO();
        loginDTO.setUser(null);
        loginDTO.setAccess_token(null);
        loginDTO.setRefresh_token(null);
        loginDTO.setToken_type(null);
        loginDTO.setExpires_in(0);
        loginDTO.setRequires_mfa(false);
        loginDTO.setMessage(ConstantsLogin.USER_DOES_NOT_EXIST);
        loginDTO.setCode(601);
        return loginDTO;
    }

}
