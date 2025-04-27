package com.henrique.login.springbooot.model;

public class PreLoginModel {
    private String email;

    public PreLoginModel(String email, String password) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
