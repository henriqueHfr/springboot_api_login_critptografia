package com.henrique.login.springbooot.controller;

import com.henrique.login.springbooot.model.CadastroModel;
import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.service.CriptografiaService;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/criptografia")
public class CriptografiaController {

    private Logger logger;
    private CriptografiaService criptografiaService;

    public CriptografiaController(CriptografiaService criptografiaService) {
        this.criptografiaService = criptografiaService;
    }


    @PostMapping("/encodeCadastro")
    public ResponseEntity<CadastroModel> encodeBody(@RequestBody CadastroModel cadastroModel){
        CadastroModel body = criptografiaService.encodeCadastroBody(cadastroModel);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/decodeCasdastro")
    public ResponseEntity<CadastroModel> decodeCadastroBody(@RequestBody  CadastroModel cadastroModel){
        CadastroModel body = criptografiaService.decodeCadastroBody(cadastroModel);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/encodeLogin")
    public ResponseEntity<LoginModel> encondeLoginBody(@RequestBody LoginModel loginModel){
        LoginModel body = criptografiaService.encodeLoginBody(loginModel);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/decodeLogin")
    public ResponseEntity<LoginModel> decodeLoginBody(@RequestBody LoginModel loginModel){
        LoginModel body = criptografiaService.decodeLoginBody(loginModel);
        return ResponseEntity.ok(body);
    }
}
