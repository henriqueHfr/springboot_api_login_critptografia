package com.henrique.login.springbooot.controller;

import com.henrique.login.springbooot.model.CadastroModel;
import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.service.LoginService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
    private Logger logger;
    private final LoginService loginService;

    public CadastroController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Void> addLogin(@Valid @RequestBody CadastroModel login){
        logger.info("Adding login");
        loginService.addLogin(login);
        return ResponseEntity.ok().build();
    }

}
