package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.model.CadastroModel;
import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.MfaModel;
import com.henrique.login.springbooot.model.PreLoginModel;
import com.henrique.login.springbooot.util.Criptografia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CriptografiaService {
    private Criptografia criptografia;
    private static final Logger log = LoggerFactory.getLogger(CriptografiaService.class);

    public CadastroModel encodeCadastroBody(CadastroModel cadastroModel){
        log.info("enconde cadastro body");
        cadastroModel.setPassword(Criptografia.encode(cadastroModel.getPassword()));
        cadastroModel.setDateOfBirth(Criptografia.encode(cadastroModel.getDateOfBirth()));
        cadastroModel.setEmail(Criptografia.encode(cadastroModel.getEmail()));
        cadastroModel.setName(Criptografia.encode(cadastroModel.getName()));
        return cadastroModel;
    }

    public LoginModel encodeLoginBody(LoginModel loginModel){
        log.info("Encode login body");
        loginModel.setPassword(Criptografia.encode(loginModel.getPassword()));
        loginModel.setEmail(Criptografia.encode(loginModel.getEmail()));
        return loginModel;
    }
    public PreLoginModel encodePreLoginBody(PreLoginModel preLoginModel){
        log.info("Encode login body");
        preLoginModel.setEmail(Criptografia.encode(preLoginModel.getEmail()));
        return preLoginModel;
    }

    public CadastroModel decodeCadastroBody(CadastroModel cadastroModel){
        log.info("Decode cadastro body");
        cadastroModel.setPassword(Criptografia.decode(cadastroModel.getPassword()));
        cadastroModel.setDateOfBirth(Criptografia.decode(cadastroModel.getDateOfBirth()));
        cadastroModel.setEmail(Criptografia.decode(cadastroModel.getEmail()));
        cadastroModel.setName(Criptografia.decode(cadastroModel.getName()));
        return cadastroModel;
    }

    public LoginModel decodeLoginBody(LoginModel loginModel){
        log.info("Decode login body");
        loginModel.setPassword(Criptografia.decode(loginModel.getPassword()));
        loginModel.setEmail(Criptografia.decode(loginModel.getEmail()));
        return loginModel;
    }
    public MfaModel decodeMfaBody(MfaModel mfaModel){
        log.info("Decode mfa body");
        mfaModel.setEmail(Criptografia.decode(mfaModel.getEmail()));
        return mfaModel;
    }

    public PreLoginModel decodePreLoginBody(PreLoginModel preLogin){
        log.info("Decode login body");
        preLogin.setEmail(Criptografia.decode(preLogin.getEmail()));
        return preLogin;
    }
}
