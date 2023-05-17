package com.metropolitan.yokaappproject.services;

import com.metropolitan.yokaappproject.domain.Korpa;
import com.metropolitan.yokaappproject.domain.Porudzbina;

import java.util.List;

public interface PorudzbinaService {

    List<Porudzbina> findAll();
    void delete(Long id);
    void update(Porudzbina porudzbina);
    void add(Porudzbina porudzbina);
    Porudzbina findByid(Long id);

}
