package com.henrique.login.springbooot.controller;

import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.PreLoginModel;
import com.henrique.login.springbooot.model.dto.PreLoginDTO;
import com.henrique.login.springbooot.service.PreLoginService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prelogin")
public class PreLoginController {

    private final PreLoginService preLoginService;
    private Logger log;

    @Autowired
    public PreLoginController(PreLoginService preLoginService) {
        this.preLoginService = preLoginService;
    }

    @PostMapping
    public ResponseEntity<PreLoginDTO> getPreLogin(@RequestBody PreLoginModel preLogin) {
        return preLoginService.getPreLoginService(preLogin);
    }
}
