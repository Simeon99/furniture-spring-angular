package com.metropolitan.yokaappproject.services;

import com.metropolitan.yokaappproject.dao.PorudzbinaDao;
import com.metropolitan.yokaappproject.domain.Porudzbina;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PorudzbinaServiceImpl implements PorudzbinaService{

    private PorudzbinaDao porudzbinaDao;

    public PorudzbinaServiceImpl(PorudzbinaDao porudzbinaDao) {
        this.porudzbinaDao = porudzbinaDao;
    }

    @Override
    public List<Porudzbina> findAll() {
        return porudzbinaDao.findAll();
    }

    @Override
    public void delete(Long id) {
        porudzbinaDao.delete(porudzbinaDao.getById(id));
    }

    @Override
    public void update(Porudzbina porudzbina) {
        porudzbinaDao.saveAndFlush(porudzbina);
    }

    @Override
    public void add(Porudzbina porudzbina) {
        porudzbinaDao.save(porudzbina);
    }

    @Override
    public Porudzbina findByid(Long id) {
        return porudzbinaDao.findById(id).get() ;
    }
}
