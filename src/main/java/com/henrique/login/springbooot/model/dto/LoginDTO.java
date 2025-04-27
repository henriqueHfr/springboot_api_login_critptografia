package com.henrique.login.springbooot.model.dto;

public class LoginDTO {
    private String email;
    private boolean mfa;
    private String message;


    public LoginDTO() {
    }

    public LoginDTO(String email, boolean mfa, String message) {
        this.email = email;
        this.mfa = mfa;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMfa() {
        return mfa;
    }
    public void setMfa(boolean mfa) {
        this.mfa = mfa;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
