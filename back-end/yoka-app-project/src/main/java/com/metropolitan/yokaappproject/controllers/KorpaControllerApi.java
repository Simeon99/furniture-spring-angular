package com.metropolitan.yokaappproject.controllers;


import com.metropolitan.yokaappproject.dao.KorpaDao;
import com.metropolitan.yokaappproject.dao.NamestajDao;
import com.metropolitan.yokaappproject.domain.Korpa;
import com.metropolitan.yokaappproject.domain.Namestaj;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/korpa")
public class KorpaControllerApi {

    private KorpaDao korpaDao;
    private NamestajDao namestajDao;

    public KorpaControllerApi(KorpaDao korpaDao, NamestajDao namestajDao) {
        this.korpaDao = korpaDao;
        this.namestajDao = namestajDao;
    }





    @GetMapping
    public ResponseEntity<List<Korpa>> getAllMembers(){
        List<Korpa> lista = (List<Korpa>) korpaDao.findAll();
        if(!lista.isEmpty())
            return new ResponseEntity<List<Korpa>>(lista, HttpStatus.OK);
        else {
            return  new ResponseEntity<List<Korpa>>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Korpa> create(){
        Korpa k = new Korpa();

        korpaDao.save(k);
        return new ResponseEntity<Korpa>(k, HttpStatus.CREATED);
    }

    @RequestMapping("{id}")
    public  ResponseEntity<Korpa> delete(@PathVariable("id")Long id){
        Korpa korpa = korpaDao.findById(id).get();
        if(korpa!= null){
            korpaDao.delete(korpa);
            return new ResponseEntity<Korpa>(korpa, HttpStatus.OK);
        }
        else return new ResponseEntity<Korpa>(HttpStatus.NOT_FOUND);
    }

        @PutMapping("{id}")
        public ResponseEntity<Korpa> edit(@PathVariable("id")Long id, @RequestBody  Long idNamestaja){
            Korpa k = korpaDao.findById(id).get();
            Namestaj n = namestajDao.findById(idNamestaja).get();
            System.out.println("AAAAAAAAA");
            korpaDao.saveAndFlush(k);
            if (k!=null) {
                k.addTem(n);
                return ResponseEntity.ok(k);
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    @GetMapping("{id}")
    public ResponseEntity<Korpa> getById(@PathVariable("id") Long id){
        Korpa korpa = korpaDao.findById(id).get();

        if(korpa !=null){
            return new ResponseEntity<Korpa>(korpa, HttpStatus.OK);
        }
        return  new ResponseEntity<Korpa>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}/namestaj")
    public ResponseEntity<Korpa> korpaAddAutomobil(@PathVariable("id")Long id, @RequestBody  Long idNamestaja){
        System.out.println("Id Korpe: "+id);
        System.out.println("Id Namestaja: "+idNamestaja);
        Optional<Korpa> korpa = Optional.ofNullable(korpaDao.findById(id).get());
        Optional<Namestaj> namestaj = Optional.ofNullable(namestajDao.findById(idNamestaja).get());

        if (korpa.isPresent()){
            korpa.get().getNamestajs().add(namestaj.get());
            korpaDao.saveAndFlush(korpa.get());
            return new ResponseEntity<Korpa>(korpa.get(), HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
    @PutMapping("{id}/namestaj/obrisi")
    public ResponseEntity<Namestaj> korpaDeleteAutomobil(@PathVariable("id")Long id, @RequestBody  Long idNamestaja){
        System.out.println("Id Korpe: "+id);
        System.out.println("Id Namestaja: "+idNamestaja);
        Optional<Korpa> korpa = Optional.ofNullable(korpaDao.findById(id).get());
        Optional<Namestaj> namestaj = Optional.ofNullable(namestajDao.findById(idNamestaja).get());

        if (korpa.isPresent()){
            korpa.get().getNamestajs().remove(namestaj.get());
            korpaDao.saveAndFlush(korpa.get());
            return new ResponseEntity<Namestaj>(namestaj.get(), HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }



}
