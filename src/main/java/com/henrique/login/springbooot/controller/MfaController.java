package com.henrique.login.springbooot.controller;

import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.MfaModel;
import com.henrique.login.springbooot.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verifyMfa")
public class MfaController {
    private final LoginService loginService;

    public MfaController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> verifyMfa(@Valid @RequestBody MfaModel mfa) {
        return loginService.verifyMfa(mfa);
    }
}
