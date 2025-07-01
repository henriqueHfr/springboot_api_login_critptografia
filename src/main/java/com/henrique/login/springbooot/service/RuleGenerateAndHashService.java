package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.model.HashVerifyModel;
import com.henrique.login.springbooot.model.ServiceModel;
import com.henrique.login.springbooot.repository.ServiceRepository;
import com.henrique.login.springbooot.util.Constants.ConstantsService;
import com.henrique.login.springbooot.util.search.SearchDataBase;
import java.util.Optional;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import com.henrique.login.springbooot.util.validate.ValidateServiceJwt;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class RuleGenerateAndHashService {

    private static final String SECRET_KEY="supersecretkeyodfnsaidfndinfasdfbdasijfnsdia143278648732@8473y412nbdsuaf";
    private static final long EXPIRATION_TIME= 3600000;
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private ServiceRepository serviceRepository;
    private SearchDataBase searchDataBase;
    private ValidateServiceJwt validateJwtService;

    public RuleGenerateAndHashService(ServiceRepository serviceRepository, SearchDataBase searchDataBase) {
        this.serviceRepository = serviceRepository;
        this.searchDataBase = searchDataBase;
        this.validateJwtService = new ValidateServiceJwt();
    }

    public ResponseEntity<ServiceModel> generateHash(String serviceName) {
        String jwt = Jwts.builder()
                    .claim("id", serviceName)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();

        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setName_service(serviceName);
        serviceModel.setJwt_token(jwt);
        serviceModel.setCreated_at(new Date().toString());

        serviceRepository.save(serviceModel);
        return ResponseEntity.ok().body(serviceModel);
    }

    public ResponseEntity<?> verifyHashService(HashVerifyModel hashVerifyModel) {
        Optional<ServiceModel> optionalService = searchDataBase.searchByServiceAndToken(
                hashVerifyModel.getServiceName(),
                hashVerifyModel.getJwtToken()
        );

        if (optionalService.isEmpty()) {
            return ResponseEntity.badRequest().body(ConstantsService.SERVICE_NOT_FOUND);
        }

        ServiceModel service = optionalService.get();

        if (!validateJwtService.validate(service.getJwt_token())) {
            return ResponseEntity.status(401).body(ConstantsService.JWT_UNAVAILABLE);
        }

        return ResponseEntity.ok().body(ConstantsService.SERVICE_AVAILABLE);
    }



}
