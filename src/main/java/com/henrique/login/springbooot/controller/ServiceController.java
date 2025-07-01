package com.henrique.login.springbooot.controller;

import com.henrique.login.springbooot.model.HashVerifyModel;
import com.henrique.login.springbooot.service.RuleGenerateAndHashService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    private RuleGenerateAndHashService ruleGenerateAndHashService;

    public ServiceController(RuleGenerateAndHashService ruleGenerateAndHashService) {
        this.ruleGenerateAndHashService = ruleGenerateAndHashService;
    }

    @PostMapping("/generateHash")
    public ResponseEntity<?> generateHashService(@Valid @RequestBody String serviceName) {
        return ruleGenerateAndHashService.generateHash(serviceName);
    }

    @PostMapping("/verifyHashService")
    public ResponseEntity<?> VerifyService(@Valid @RequestBody HashVerifyModel hashVerifyModel) {
        return ruleGenerateAndHashService.verifyHashService(hashVerifyModel);
    }
}
