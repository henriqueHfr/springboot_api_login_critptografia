package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.PreLoginModel;
import com.henrique.login.springbooot.model.UserModel;
import com.henrique.login.springbooot.model.dto.PreLoginDTO;
import com.henrique.login.springbooot.repository.EnterpriseRepository;
import com.henrique.login.springbooot.repository.UserRepository;
import com.henrique.login.springbooot.Constants.ConstantsPreLogin;
import com.henrique.login.springbooot.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.model.PublishResponse;


@Service
public class PreLoginService {

    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;
    PreLoginDTO preLoginDTO = new PreLoginDTO();

    @Autowired
    private SearchDataBase searchDataBase;

    @Autowired
    private ReturnUserNotFound returnUserNotFound;

    private CriptografiaService criptografiaService;

    private SendEmailService sendEmailService;

    @Autowired
    public PreLoginService(UserRepository userRepository, EnterpriseRepository enterpriseRepository, CriptografiaService criptografiaService, SendEmailService sendEmailService) {
        this.criptografiaService = new CriptografiaService();
        this.sendEmailService = sendEmailService;
        this.userRepository = userRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    public ResponseEntity<PreLoginDTO> getPreLoginService(PreLoginModel preLogin) {
        PreLoginModel preLoginDecrypted = criptografiaService.decodePreLoginBody(preLogin);
        UserModel user = searchDataBase.searchUserByEmail(preLoginDecrypted);

        if (user == null) {
            PreLoginDTO userNotFound = ReturnUserNotFound.returnUserNotFoundPreLogin();
            return ResponseEntity.badRequest().body(userNotFound);
        }

        boolean isPasswordExpired = IsPasswordExpired.isPasswordExpired(user);

        preLoginDTO.setUser_exists(true);
        preLoginDTO.setPassword_expired(isPasswordExpired);
        preLoginDTO.setRequires_mfa(user.getEnterprise() != null ? user.getEnterprise().isRequiresMfa() : false);

        if (user.getEnterprise() != null) {
            preLoginDTO.setSso_available(user.getEnterprise().getSsoAvailable());
        } else {
            preLoginDTO.setSso_available(false);
            preLoginDTO.setMessage(ConstantsPreLogin.FAILED_TO_SEARCH_ENTERPRISE);
            return ResponseEntity.badRequest().body(preLoginDTO);
        }

        if (isPasswordExpired) {
            preLoginDTO.setMessage(ConstantsPreLogin.PASSWORD_EXPIRED);
        } else if (user.getEnterprise() != null && user.getEnterprise().isRequiresMfa()) {
            preLoginDTO.setMessage(ConstantsPreLogin.USER_EXISTS_AND_MULTIFACTOR_MANDATORY);
        } else {
            preLoginDTO.setMessage(ConstantsPreLogin.USER_EXISTS_AND_MULTIFACTOR_MANDATORY);
        }



        return ResponseEntity.ok().body(preLoginDTO);
    }
}
