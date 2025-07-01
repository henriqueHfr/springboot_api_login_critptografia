package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.util.Constants.ConstantsLogin;
import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.MfaModel;
import com.henrique.login.springbooot.model.UserModel;
import com.henrique.login.springbooot.model.dto.LoginDTO;
import com.henrique.login.springbooot.model.dto.MfaDTO;
import com.henrique.login.springbooot.repository.UserRepository;
import com.henrique.login.springbooot.util.*;
import com.henrique.login.springbooot.util.responses.ResponseLogin;
import com.henrique.login.springbooot.util.responses.ResponseMFA;
import com.henrique.login.springbooot.util.search.SearchDataBase;
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
        LoginDTO loginDTO = ResponseLogin.buildLoginReponse(user);
        if (loginDTO.isMfa()) {
            int number = GenerateMfaNumber.generateMfaCode();
            user.setMfaCode(number);
            userRepository.save(user);
            String mensagem = ConstantsLogin.EMAIL_MESSAGE + String.valueOf(number);
            sendEmailService.sendSms(user.getPhone(), mensagem);
        }
        return ResponseEntity.ok(loginDTO);
    }

    public ResponseEntity<?> verifyMfa(MfaModel mfa) {
        MfaModel decryptedMfa = criptografiaService.decodeMfaBody(mfa);
        UserModel user = searchDataBase.searchMfaByEmail(decryptedMfa);

        if (user == null) {
            return ResponseEntity.badRequest().body(ReturnUserNotFound.returnUserNotFoundLogin());
        }

        if (user.getMfaCode() == 0 &&  mfa.getMfaCode() == 0) {
            return ResponseEntity.badRequest().body(ConstantsLogin.MFA_NOT_FOUND);
        }

        if (user.getMfaCode() != mfa.getMfaCode()) {
            return ResponseEntity.badRequest().body(ConstantsLogin.MFA_CODE_NOT_MATCH);
        }

        MfaDTO mfaDTO = ResponseMFA.buildMfaResponse(user);
        return ResponseEntity.ok(mfaDTO);
    }

}
