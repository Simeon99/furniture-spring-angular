package com.metropolitan.yokaappproject.domain;

public class Dto {

    private String email;
    private String password;

    public Dto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Dto() {
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
