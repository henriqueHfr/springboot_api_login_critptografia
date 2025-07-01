package com.henrique.login.springbooot.util.search;

import com.henrique.login.springbooot.model.*;
import com.henrique.login.springbooot.repository.ServiceRepository;
import com.henrique.login.springbooot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchDataBase {

    private UserRepository userRepository;
    private ServiceRepository serviceRepository;

    public SearchDataBase(UserRepository userRepository, ServiceRepository serviceRepository) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
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
    public Optional<ServiceModel> searchByServiceAndToken(String name_service, String jwt_token) {
        return serviceRepository.findByNameServiceAndJwtToken(name_service, jwt_token);
    }
}
