package com.henrique.login.springbooot.model;

public class MfaModel {
    public String email;

    public int mfaCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMfaCode() {
        return mfaCode;
    }

    public void setMfaCode(int mfaCode) {
        this.mfaCode = mfaCode;
    }
}
