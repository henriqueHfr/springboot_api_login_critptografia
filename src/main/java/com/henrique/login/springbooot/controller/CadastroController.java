package com.henrique.login.springbooot.controller;

import com.henrique.login.springbooot.model.CadastroModel;
import com.henrique.login.springbooot.service.CriptografiaService;
import com.henrique.login.springbooot.service.LoginService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
    private static final Logger log = LoggerFactory.getLogger(CadastroController.class);

    @PostMapping
    public ResponseEntity<Void> addLogin(@Valid @RequestBody CadastroModel login){
        log.info("Adding login");
        return ResponseEntity.ok().build();
    }

}
