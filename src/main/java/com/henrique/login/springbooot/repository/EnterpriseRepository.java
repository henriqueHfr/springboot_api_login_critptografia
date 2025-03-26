package com.henrique.login.springbooot.repository;

import com.henrique.login.springbooot.model.EnterpriseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<EnterpriseModel, Long> {
}
