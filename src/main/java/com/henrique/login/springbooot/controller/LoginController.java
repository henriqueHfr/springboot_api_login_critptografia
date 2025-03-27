package com.henrique.login.springbooot.controller;

import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> verifyLogin(@Valid @RequestBody LoginModel login){
        return loginService.verifyLoginAndGenrateToken(login);
    }
}
