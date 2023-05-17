package com.metropolitan.yokaappproject.services;

import com.metropolitan.yokaappproject.dao.NamestajDao;
import com.metropolitan.yokaappproject.domain.Namestaj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamestajServiceImpl implements NamestajService{

    private NamestajDao namestajDao;

    public NamestajServiceImpl(NamestajDao namestajDao) {
        this.namestajDao = namestajDao;
    }

    @Override
    public List<Namestaj> findAll() {
        return namestajDao.findAll();
    }

    @Override
    public void delete(Long id) {
        namestajDao.delete(namestajDao.getById(id));
    }

    @Override
    public void update(Namestaj namestaj) {
        namestajDao.saveAndFlush(namestaj);
    }

    @Override
    public void add(Namestaj namestaj) {
        namestajDao.save(namestaj);
    }

    @Override
    public Namestaj findByid(Long id) {
        return namestajDao.findById(id).get();
    }
}
