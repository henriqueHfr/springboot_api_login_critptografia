package com.henrique.login.springbooot.model;

import jakarta.validation.constraints.NotNull;

public class LoginModel {
    @NotNull
    private String email;
    @NotNull
    private String password;

    public LoginModel(final String email, final String password) {
        this.email = email;
        this.password = password;
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
}
