package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.Constants.ConstantsLogin;
import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.MfaModel;
import com.henrique.login.springbooot.model.UserModel;
import com.henrique.login.springbooot.model.dto.LoginDTO;
import com.henrique.login.springbooot.model.dto.MfaDTO;
import com.henrique.login.springbooot.model.dto.Response.UserResponseDTO;
import com.henrique.login.springbooot.repository.UserRepository;
import com.henrique.login.springbooot.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    private SearchDataBase searchDataBase;

    private UserRepository userRepository;

    private SendEmailService sendEmailService;

    private CriptografiaService criptografiaService;

    public LoginService(CriptografiaService criptografiaService, SearchDataBase searchDataBase, SendEmailService sendEmailService, UserRepository userRepository) {
        this.criptografiaService = criptografiaService;
        this.sendEmailService = sendEmailService;
        this.searchDataBase = searchDataBase;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> verifyLoginAndGenrateToken(LoginModel login){
        LoginModel loginDescriptografado = criptografiaService.decodeLoginBody(login);
        UserModel user = searchDataBase.searchUserByEmailAndPassword(loginDescriptografado);

        if(user == null){
            MfaDTO returnUserNotFound = ReturnUserNotFound.returnUserNotFoundLogin();
            return ResponseEntity.badRequest().body(returnUserNotFound);
        }

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(user.getEmail());
        loginDTO.setMfa(user.getEnterprise() != null ? user.getEnterprise().isRequiresMfa() : false);
        loginDTO.setMessage("usuário e senha corretos");

        if (loginDTO.isMfa()) {
            int number = GenerateMfaNumber.generateMfaCode();

            user.setMfaCode(number);

            userRepository.save(user);

            sendEmailService.sendSms(user.getPhone(), "Auth: " + number + ". Olá sergio, funcionou o envio de sms");
        }
        return ResponseEntity.ok(loginDTO);
    }

    public ResponseEntity<?> verifyMfa(MfaModel mfa) {
        MfaModel mfaDescriptografado = criptografiaService.decodeMfaBody(mfa);
        UserModel user = searchDataBase.searchMfaByEmail(mfaDescriptografado);

        if (user == null) {
            MfaDTO returnUserNotFound = ReturnUserNotFound.returnUserNotFoundLogin();
            return ResponseEntity.badRequest().body(returnUserNotFound);
        }

        if (user.getMfaCode() == 0 || mfa.getMfaCode() == 0) {
            return ResponseEntity.badRequest().body("MFA code not found.");
        }

        if (user.getMfaCode() != mfa.getMfaCode()) {
            return ResponseEntity.badRequest().body("Invalid MFA code.");
        }

        String accessToken = JwtUtil.generateToken(user.getId(), user.getEmail());
        String refreshToken = JwtUtil.generateRefreshToken(user.getId());

        UserResponseDTO userResponseDTO = new UserResponseDTO(
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
        mfaDTO.setUser(userResponseDTO);
        mfaDTO.setRequires_mfa(user.getEnterprise() != null && user.getEnterprise().isRequiresMfa());
        mfaDTO.setMessage(ConstantsLogin.LOGIN_SUCCESS);
        mfaDTO.setCode(200);

        return ResponseEntity.ok(mfaDTO);
    }

}
