package com.metropolitan.yokaappproject.services;

import com.metropolitan.yokaappproject.dao.KorpaDao;
import com.metropolitan.yokaappproject.domain.Korpa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorpaServiceImpl implements KorpaService{

    private KorpaDao korpaDao;

    public KorpaServiceImpl(KorpaDao korpaDao) {
        this.korpaDao = korpaDao;
    }

    @Override
    public List<Korpa> findAll() {
        return korpaDao.findAll();
    }

    @Override
    public void delete(Long id) {
        korpaDao.delete(korpaDao.getById(id));
    }

    @Override
    public void update(Korpa korpa) {
        korpaDao.saveAndFlush(korpa);

    }

    @Override
    public void add(Korpa korpa) {
        korpaDao.save(korpa);
    }

    @Override
    public Korpa findByid(Long id) {
        return korpaDao.findById(id).get();
    }
}
