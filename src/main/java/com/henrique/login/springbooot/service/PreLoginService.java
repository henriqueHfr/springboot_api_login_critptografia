package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.model.EnterpriseModel;
import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.UserModel;
import com.henrique.login.springbooot.model.dto.PreLoginDTO;
import com.henrique.login.springbooot.repository.EnterpriseRepository;
import com.henrique.login.springbooot.repository.UserRepository;
import com.henrique.login.springbooot.util.Constants.ConstantsPreLogin;
import com.henrique.login.springbooot.util.IsPasswordExpired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class PreLoginService {

    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;
    PreLoginDTO preLoginDTO = new PreLoginDTO();

    @Autowired
    public PreLoginService(UserRepository userRepository, EnterpriseRepository enterpriseRepository) {
        this.userRepository = userRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    public ResponseEntity<PreLoginDTO> getPreLoginService(LoginModel login) {
        UserModel user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(login.getEmail()))
                .findAny()
                .orElse(null);

        if (user == null) {
            preLoginDTO.setUser_exists(false);
            preLoginDTO.setPassword_expired(false);
            preLoginDTO.setRequires_mfa(false);
            preLoginDTO.setSso_available(false);
            preLoginDTO.setMessage("User does not exist");
            return ResponseEntity.badRequest().body(preLoginDTO);
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
