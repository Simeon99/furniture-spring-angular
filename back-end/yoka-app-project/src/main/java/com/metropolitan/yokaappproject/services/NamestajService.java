package com.metropolitan.yokaappproject.services;

import com.metropolitan.yokaappproject.domain.Korpa;
import com.metropolitan.yokaappproject.domain.Namestaj;

import java.util.List;

public interface NamestajService {

    List<Namestaj> findAll();
    void delete(Long id);
    void update(Namestaj namestaj);
    void add(Namestaj namestaj);
    Namestaj findByid(Long id);

}
