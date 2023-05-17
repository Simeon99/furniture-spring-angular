package com.metropolitan.yokaappproject.services;

import com.metropolitan.yokaappproject.domain.Korisnik;
import com.metropolitan.yokaappproject.domain.Korpa;

import java.util.List;

public interface KorpaService {

    List<Korpa> findAll();
    void delete(Long id);
    void update(Korpa korpa);
    void add(Korpa korpa);
    Korpa findByid(Long id);
}
