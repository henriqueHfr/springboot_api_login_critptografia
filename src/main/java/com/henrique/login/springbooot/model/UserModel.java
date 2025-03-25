package com.henrique.login.springbooot.model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(name = "email")
    @UniqueElements
    private String email;
    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "password_expires")
    private LocalDate passwordExpires;

    private List<String> ssoAvailable;
    private boolean requiresMfa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<String> getSsoAvailable() {
        return ssoAvailable;
    }

    public void setSsoAvailable(List<String> ssoAvailable) {
        this.ssoAvailable = ssoAvailable;
    }

    public boolean isRequiresMfa() {
        return requiresMfa;
    }

    public void setRequiresMfa(boolean requiresMfa) {
        this.requiresMfa = requiresMfa;
    }
}
