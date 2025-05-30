package com.henrique.login.springbooot.util;

import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.MfaModel;
import com.henrique.login.springbooot.model.PreLoginModel;
import com.henrique.login.springbooot.model.UserModel;
import com.henrique.login.springbooot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchDataBase {

    private UserRepository userRepository;

    public SearchDataBase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel searchUserByEmailAndPassword(LoginModel login) {
        return userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(login.getEmail()))
                .filter(u -> u.getPassword().equals(login.getPassword()))
                .findAny()
                .orElse(null);
    }
    public UserModel searchUserByEmail(PreLoginModel preLogin) {
        return userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(preLogin.getEmail()))
                .findAny()
                .orElse(null);
    }

    public UserModel searchMfaByEmail(MfaModel mfaModel) {
        return userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(mfaModel.getEmail()))
                .findAny()
                .orElse(null);
    }
}
