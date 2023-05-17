package com.metropolitan.yokaappproject.services;

import com.metropolitan.yokaappproject.dao.KorisnikDao;
import com.metropolitan.yokaappproject.domain.Korisnik;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikServiceImpl implements KorisnikService{


    private KorisnikDao korisnikDao;

    public KorisnikServiceImpl(KorisnikDao korisnikDao) {
        this.korisnikDao = korisnikDao;
    }

    @Override
    public List<Korisnik> findAll() {
        return korisnikDao.findAll();
    }

    @Override
    public void delete(Long id) {
        korisnikDao.delete(korisnikDao.getById(id));
    }

    @Override
    public void update(Korisnik korisnik) {
        korisnikDao.saveAndFlush(korisnik);
    }

    @Override
    public void add(Korisnik korisnik) {
        korisnikDao.save(korisnik);
    }

    @Override
    public Korisnik findByid(Long id) {
        return korisnikDao.findById(id).get();
    }
}
