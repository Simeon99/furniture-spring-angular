package com.metropolitan.yokaappproject.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "korisnik")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_korisnika")
    private Long idKorinsika;
    @Column(name = "ime_korisnka")
    private String imeKorisnka;
    @Column(name = "email_korisnka")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_korisnka")
    private String phone;
    @Column(name = "role_korisnka")
    private String role;

    @OneToMany(mappedBy="korisnik")
    private Set<Porudzbina> porudzbine;


    public Korisnik(
                    String imeKorisnka,
                    String email,
                    String password,
                    String phone,
                    String role) {
        this.idKorinsika = idKorinsika;
        this.imeKorisnka = imeKorisnka;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public Korisnik() {
    }

    public Long getIdKorinsika() {
        return idKorinsika;
    }

    public void setIdKorinsika(Long idKorinsika) {
        this.idKorinsika = idKorinsika;
    }

    public String getImeKorisnka() {
        return imeKorisnka;
    }

    public void setImeKorisnka(String imeKorisnka) {
        this.imeKorisnka = imeKorisnka;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "idKorinsika=" + idKorinsika +
                ", imeKorisnka='" + imeKorisnka + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
