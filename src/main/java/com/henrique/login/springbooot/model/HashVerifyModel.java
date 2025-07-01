package com.henrique.login.springbooot.model;

public class HashVerifyModel {

    private String serviceName;
    private String jwtToken;

    public HashVerifyModel() {
    }

    public HashVerifyModel(String serviceName, String jwtToken) {
        this.serviceName = serviceName;
        this.jwtToken = jwtToken;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
