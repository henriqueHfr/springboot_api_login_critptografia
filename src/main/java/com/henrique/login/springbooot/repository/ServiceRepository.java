package com.henrique.login.springbooot.repository;

import com.henrique.login.springbooot.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {
    List<ServiceModel> findByNameService(String name_service);

    Optional<ServiceModel> findByNameServiceAndJwtToken(String name_service, String jwt_token);

}
