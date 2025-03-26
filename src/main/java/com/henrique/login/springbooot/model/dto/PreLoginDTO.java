package com.henrique.login.springbooot.model.dto;

import java.lang.reflect.Array;
import java.util.List;

public class PreLoginDTO {
    private boolean user_exists;
    private boolean requires_mfa;
    private boolean password_expired;
    private boolean sso_available;
    private String message;


    public boolean isUser_exists() {
        return user_exists;
    }

    public void setUser_exists(boolean user_exists) {
        this.user_exists = user_exists;
    }

    public boolean isRequires_mfa() {
        return requires_mfa;
    }

    public void setRequires_mfa(boolean requires_mfa) {
        this.requires_mfa = requires_mfa;
    }

    public boolean isPassword_expired() {
        return password_expired;
    }

    public void setPassword_expired(boolean password_expired) {
        this.password_expired = password_expired;
    }

    public boolean getSso_available() {
        return sso_available;
    }

    public void setSso_available(boolean sso_available) {
        this.sso_available = sso_available;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
