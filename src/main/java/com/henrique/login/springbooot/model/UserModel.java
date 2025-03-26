package com.henrique.login.springbooot.model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "email")
    @UniqueElements
    private String email;
    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "password_expires")
    private LocalDate passwordExpires;

    @ManyToOne
    @JoinColumn(name = "enterprise_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_enterprise_user"))
    private EnterpriseModel enterprise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getPasswordExpires() {
        return passwordExpires;
    }

    public void setPasswordExpires(LocalDate passwordExpires) {
        this.passwordExpires = passwordExpires;
    }

    public EnterpriseModel getEnterprise() { return enterprise; }
    public void setEnterprise(EnterpriseModel enterprise) { this.enterprise = enterprise; }
}
