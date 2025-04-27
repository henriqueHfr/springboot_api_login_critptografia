package com.henrique.login.springbooot.model.dto;

import com.henrique.login.springbooot.model.dto.Response.UserResponseDTO;

public class MfaDTO {

    private String access_token;
    private String refresh_token;
    private String token_type;
    private long expires_in;
    private UserResponseDTO user;
    private boolean requires_mfa;
    private String message;
    private int code;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public boolean getRequires_mfa() {
        return requires_mfa;
    }

    public void setRequires_mfa(boolean requires_mfa) {
        this.requires_mfa = requires_mfa;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
