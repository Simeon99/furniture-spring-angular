package com.metropolitan.yokaappproject.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "porudzbina")
public class Porudzbina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_porudzbine")
    private Long idPoridzbine;
    @Column(name = "ime_kupca")
    @NotNull
    private String imeKupca;
    @Column(name = "prezime_kupca")
    @NotNull
    private String prezime;
    @Column(name = "grad")
    @NotNull
    private String grad;
    @Column(name = "adresa")
    @NotNull
    private String adresa;
    @Column(name= "br_telefona")
    @NotNull
    private String brojTeleofna;
    @ManyToOne
    @JoinColumn(name="id_korisnika")
    private Korisnik korisnik;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(

            name = "namesta_porudzbina",
            joinColumns = @JoinColumn(name = "id_porudzbine"),
            inverseJoinColumns = @JoinColumn(name = "id_namestaj"))
    private Set<Namestaj> listaNamestaja;

    public Porudzbina(String imeKupca, String prezime, String grad, String adresa, String brojTeleofna, Korisnik korisnik) {

        this.imeKupca = imeKupca;
        this.prezime = prezime;
        this.grad = grad;
        this.adresa = adresa;
        this.brojTeleofna = brojTeleofna;
        this.korisnik = korisnik;
    }

    public Porudzbina() {
    }

    public String getImeKupca() {
        return imeKupca;
    }

    public void setImeKupca(String imeKupca) {
        this.imeKupca = imeKupca;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTeleofna() {
        return brojTeleofna;
    }

    public void setBrojTeleofna(String brojTeleofna) {
        this.brojTeleofna = brojTeleofna;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Long getIdPoridzbine() {
        return idPoridzbine;
    }

    public void setIdPoridzbine(Long idPoridzbine) {
        this.idPoridzbine = idPoridzbine;
    }

    public Set<Namestaj> getListaNamestaja() {
        return listaNamestaja;
    }

    public void setListaNamestaja(Set<Namestaj> listaNamestaja) {
        this.listaNamestaja = listaNamestaja;
    }

    @Override
    public String toString() {
        return "Porudzbina{" +
                "idPoridzbine=" + idPoridzbine +
                ", imeKupca='" + imeKupca + '\'' +
                ", prezime='" + prezime + '\'' +
                ", grad='" + grad + '\'' +
                ", adresa='" + adresa + '\'' +
                ", brojTeleofna='" + brojTeleofna + '\'' +
                ", korisnik=" + korisnik +
                ", listaNamestaja=" + listaNamestaja +
                '}';
    }
}
