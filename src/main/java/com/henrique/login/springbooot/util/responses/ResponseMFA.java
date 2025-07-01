package com.henrique.login.springbooot.util.responses;

import com.henrique.login.springbooot.model.UserModel;
import com.henrique.login.springbooot.model.dto.MfaDTO;
import com.henrique.login.springbooot.model.dto.Response.UserResponseDTO;
import com.henrique.login.springbooot.util.Constants.ConstantsLogin;
import com.henrique.login.springbooot.util.JwtUtil;

public class ResponseMFA {

    public static MfaDTO buildMfaResponse(UserModel user) {
        String accessToken = JwtUtil.generateToken(user.getId(), user.getEmail());
        String refreshToken = JwtUtil.generateRefreshToken(user.getId());

        UserResponseDTO userDTO = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getDateOfBirth()
        );

        MfaDTO mfaDTO = new MfaDTO();
        mfaDTO.setAccess_token(accessToken);
        mfaDTO.setRefresh_token(refreshToken);
        mfaDTO.setToken_type("Bearer");
        mfaDTO.setExpires_in(3600);
        mfaDTO.setUser(userDTO);
        mfaDTO.setRequires_mfa(user.getEnterprise() != null && user.getEnterprise().isRequiresMfa());
        mfaDTO.setMessage(ConstantsLogin.LOGIN_SUCCESS);
        mfaDTO.setCode(200);

        return mfaDTO;
    }
}
