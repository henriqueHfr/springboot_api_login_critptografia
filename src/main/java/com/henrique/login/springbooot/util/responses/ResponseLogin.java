package com.henrique.login.springbooot.util.responses;

import com.henrique.login.springbooot.model.UserModel;
import com.henrique.login.springbooot.model.dto.LoginDTO;

public class ResponseLogin {
    public static LoginDTO buildLoginReponse(UserModel user) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(user.getEmail());
        loginDTO.setMfa(user.getEnterprise() != null && user.getEnterprise().isRequiresMfa());
        loginDTO.setMessage("usuário e senha corretos");

        return loginDTO;
    }
}
