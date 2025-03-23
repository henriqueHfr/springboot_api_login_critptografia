package com.henrique.login.springbooot.controller;

import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.service.LoginService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {
    private Logger logger;
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Void> verifyLogin(@Valid @RequestBody LoginModel login){
        logger.info("Verifying login");
        LoginModel loginModel = loginService.verifyLogin(login);
        if(loginModel == null){
            return ResponseEntity.badRequest().build();
        }
        System.out.println(loginModel);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<LoginModel> getLogin(){
        logger.info("Getting login");
        LoginModel login = loginService.getLogin();
        if(login == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(login);
    }
}
