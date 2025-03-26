package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.model.CadastroModel;
import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.UserModel;
import com.henrique.login.springbooot.repository.UserRepository;
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

    private UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addLogin(CadastroModel login) {
        logger.info("Adding login");
        UserModel user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(login.getEmail()))
                .findAny()
                .orElse(null);
        return;
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

}
