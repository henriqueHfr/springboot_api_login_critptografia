package com.henrique.login.springbooot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "enterprise")
public class EnterpriseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "sso_available")
    private boolean ssoAvailable;

    private boolean requiresMfa;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean getSsoAvailable() { return ssoAvailable; }
    public void setSsoAvailable(boolean ssoAvailable) { this.ssoAvailable = ssoAvailable; }

    public boolean isRequiresMfa() { return requiresMfa; }
    public void setRequiresMfa(boolean requiresMfa) { this.requiresMfa = requiresMfa; }
}
