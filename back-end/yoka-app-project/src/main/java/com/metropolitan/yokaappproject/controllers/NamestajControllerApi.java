package com.metropolitan.yokaappproject.controllers;


import com.metropolitan.yokaappproject.dao.NamestajDao;
import com.metropolitan.yokaappproject.domain.Namestaj;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/namestaj")
public class NamestajControllerApi {


    NamestajDao namestajDao;

    public NamestajControllerApi(NamestajDao namestajDao) {
        this.namestajDao = namestajDao;
    }

    @GetMapping
    public ResponseEntity<List<Namestaj>> getAllMembers(){
        List<Namestaj> lista = (List<Namestaj>) namestajDao.findAll();
        if(!lista.isEmpty())
            return new ResponseEntity<List<Namestaj>>(lista, HttpStatus.OK);
        else {
            return  new ResponseEntity<List<Namestaj>>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Namestaj> create(@RequestBody Namestaj namestaj){
        Namestaj n = namestaj;
        namestajDao.save(n);
        return new ResponseEntity<Namestaj>(namestaj, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<Namestaj> delete(@PathVariable("id")Long id){
        Namestaj namestaj = namestajDao.findById(id).get();
        if(namestaj!= null){
            namestajDao.delete(namestaj);
            return new ResponseEntity<Namestaj>(namestaj, HttpStatus.OK);
        }
        else return new ResponseEntity<Namestaj>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Namestaj> edit(@PathVariable("id")Long id, @RequestBody  Namestaj namestaj){
        if (Objects.equals(id, namestaj.getIdNamestaja())) {
            namestajDao.saveAndFlush(namestaj);
            return ResponseEntity.ok(namestaj);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<Namestaj> getById(@PathVariable("id") Long id){
        Namestaj namestaj = namestajDao.findById(id).get();

        if(namestaj !=null){
            return new ResponseEntity<Namestaj>(namestaj, HttpStatus.OK);
        }
        return  new ResponseEntity<Namestaj>(HttpStatus.NOT_FOUND);
    }




}
