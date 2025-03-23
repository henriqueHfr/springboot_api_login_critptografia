package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.model.CadastroModel;
import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.util.Criptografia;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class LoginService {

    private Logger logger;
    private Criptografia criptografia;
    private final Queue<LoginModel> loginQueue = new ConcurrentLinkedDeque<>();

    public void addLogin(CadastroModel login) {
        logger.info("Adding login");
        String user = criptografia.decode(login.getEmail());
        String password = criptografia.decode(login.getPassword());
        loginQueue.add(new LoginModel(user, password));
    }

    public LoginModel getLogin() {
        logger.info("Getting login");
        return loginQueue.stream()
                .findAny()
                .orElse(null);
    }

    public LoginModel verifyLogin(LoginModel login){
        logger.info("Verifying login");
        String user = criptografia.decode(login.getPassword());
        String password = criptografia.decode(login.getPassword());
        return loginQueue.stream()
                .filter(l -> l.getEmail().equals(user) && l.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void clearLogin(){
        logger.info("Clearing login");
        loginQueue.clear();
    }
}
