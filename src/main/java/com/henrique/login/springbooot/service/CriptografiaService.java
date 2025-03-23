package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.model.CadastroModel;
import com.henrique.login.springbooot.model.LoginModel;
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
        cadastroModel.setPassword(criptografia.encode(cadastroModel.getPassword()));
        cadastroModel.setDateOfBirth(criptografia.encode(cadastroModel.getDateOfBirth()));
        cadastroModel.setEmail(criptografia.encode(cadastroModel.getEmail()));
        cadastroModel.setName(criptografia.encode(cadastroModel.getName()));
        return cadastroModel;
    }

    public LoginModel encodeLoginBody(LoginModel loginModel){
        log.info("Encode login body");
        loginModel.setPassword(criptografia.encode(loginModel.getPassword()));
        loginModel.setEmail(criptografia.encode(loginModel.getEmail()));
        return loginModel;
    }

    public CadastroModel decodeCadastroBody(CadastroModel cadastroModel){
        log.info("Decode cadastro body");
        cadastroModel.setPassword(criptografia.decode(cadastroModel.getPassword()));
        cadastroModel.setDateOfBirth(criptografia.decode(cadastroModel.getDateOfBirth()));
        cadastroModel.setEmail(criptografia.decode(cadastroModel.getEmail()));
        cadastroModel.setName(criptografia.decode(cadastroModel.getName()));
        return cadastroModel;
    }

    public LoginModel decodeLoginBody(LoginModel loginModel){
        log.info("Decode login body");
        loginModel.setPassword(criptografia.decode(loginModel.getPassword()));
        loginModel.setEmail(criptografia.decode(loginModel.getEmail()));
        return loginModel;
    }
}
