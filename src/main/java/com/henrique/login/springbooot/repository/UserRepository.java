package com.henrique.login.springbooot.repository;

import com.henrique.login.springbooot.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
