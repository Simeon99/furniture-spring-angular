package com.metropolitan.yokaappproject.services;

import com.metropolitan.yokaappproject.domain.Korisnik;

import java.util.List;

public interface KorisnikService {

    List<Korisnik> findAll();
    void delete(Long id);
    void update(Korisnik korisnik);
    void add(Korisnik korisnik);
    Korisnik findByid(Long id);

}
