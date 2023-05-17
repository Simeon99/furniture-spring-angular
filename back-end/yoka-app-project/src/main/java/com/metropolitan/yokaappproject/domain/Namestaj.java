package com.metropolitan.yokaappproject.domain;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "namestaj")
public class Namestaj {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_namestaj")
    private Long idNamestaja;
    @Column(name = "naziv_namestaja")
    private String naziv;
    @Column(name = "cena_namestaja")
    private double cena;
    @Column(name = "opis_namestaja")
    private String opis;
    @Column(name = "visina_namestaja")
    private double visina;
    @Column(name = "sirina_namestaja")
    private double sirina;
    @Column(name = "dubina_namestaja")
    private double dubina;
    @Column(name = "url_slike")
    private String urlSlika;

    public Namestaj(Long idNamestaja,
                    String naziv,
                    double cena,
                    String opis,
                    double visina,
                    double sirina,
                    double dubina,
                    String urlSlika) {
        this.idNamestaja = idNamestaja;
        this.naziv = naziv;
        this.cena = cena;
        this.opis = opis;
        this.visina = visina;
        this.sirina = sirina;
        this.dubina = dubina;
        this.urlSlika = urlSlika;
    }

    public Namestaj() {
    }

    public Long getIdNamestaja() {
        return idNamestaja;
    }

    public void setIdNamestaja(Long idNamestaja) {
        this.idNamestaja = idNamestaja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getVisina() {
        return visina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    public double getSirina() {
        return sirina;
    }

    public void setSirina(double sirina) {
        this.sirina = sirina;
    }

    public double getDubina() {
        return dubina;
    }

    public void setDubina(double dubina) {
        this.dubina = dubina;
    }

    public String getUrlSlika() {
        return urlSlika;
    }

    public void setUrlSlika(String urlSlika) {
        this.urlSlika = urlSlika;
    }

    @Override
    public String toString() {
        return "Namestaj{" +
                "idNamestaja=" + idNamestaja +
                ", naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", opis='" + opis + '\'' +
                ", visina=" + visina +
                ", sirina=" + sirina +
                ", dubina=" + dubina +
                ", urlSlika='" + urlSlika + '\'' +
                '}';
    }
}
