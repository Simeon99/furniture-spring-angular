package com.metropolitan.yokaappproject.controllers;


import com.metropolitan.yokaappproject.dao.NamestajDao;
import com.metropolitan.yokaappproject.dao.PorudzbinaDao;
import com.metropolitan.yokaappproject.domain.Korpa;
import com.metropolitan.yokaappproject.domain.Namestaj;
import com.metropolitan.yokaappproject.domain.Porudzbina;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/porudzbina")
public class PorudzbinaControllerApi {

    private PorudzbinaDao porudzbinaDao;
    private NamestajDao namestajDao;

    public PorudzbinaControllerApi(PorudzbinaDao porudzbinaDao, NamestajDao namestajDao) {
        this.porudzbinaDao = porudzbinaDao;
        this.namestajDao = namestajDao;
    }

    @GetMapping
    public ResponseEntity<List<Porudzbina>> getAllMembers(){
        List<Porudzbina> lista = (List<Porudzbina>) porudzbinaDao.findAll();
        if(!lista.isEmpty())
            return new ResponseEntity<List<Porudzbina>>(lista, HttpStatus.OK);
        else {
            return  new ResponseEntity<List<Porudzbina>>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Porudzbina> create( @RequestBody Porudzbina porudzbina){
        Porudzbina p = porudzbina;
        List<Namestaj> listaNamestaja = new ArrayList<>();
        listaNamestaja.clear();
        listaNamestaja.addAll(p.getListaNamestaja());

        System.out.println("Lista namestaja"+ p.getListaNamestaja().size());
        p.getListaNamestaja().clear();
        porudzbinaDao.save(p);
        for(Namestaj n : listaNamestaja){
            addNamestaj(p.getIdPoridzbine(),n.getIdNamestaja());
        }

        System.out.println("Porudzbina"+p);

        return new ResponseEntity<Porudzbina>(porudzbina, HttpStatus.CREATED);
    }

    @RequestMapping("{id}")
    public  ResponseEntity<Porudzbina> delete(@PathVariable("id")Long id){
        Porudzbina porudzbina = porudzbinaDao.findById(id).get();
        if(porudzbina!= null){
            porudzbinaDao.delete(porudzbina);
            return new ResponseEntity<Porudzbina>(porudzbina, HttpStatus.OK);
        }
        else return new ResponseEntity<Porudzbina>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Porudzbina> edit(@PathVariable("id")Long id, @RequestBody  Porudzbina porudzbina){
        if (Objects.equals(id, porudzbina.getIdPoridzbine())) {
            porudzbinaDao.saveAndFlush(porudzbina);
            return ResponseEntity.ok(porudzbina);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<Porudzbina> getById(@PathVariable("id") Long id){
        Porudzbina porudzbina = porudzbinaDao.findById(id).get();

        if(porudzbina !=null){
            return new ResponseEntity<Porudzbina>(porudzbina, HttpStatus.OK);
        }
        return  new ResponseEntity<Porudzbina>(HttpStatus.NOT_FOUND);
    }

    public void addNamestaj(Long id, Long idNamestaja){
        Optional<Porudzbina> porudzbina = Optional.ofNullable(porudzbinaDao.findById(id).get());
        Optional<Namestaj> namestaj = Optional.ofNullable(namestajDao.findById(idNamestaja).get());

        if (porudzbina.isPresent()){
            porudzbina.get().getListaNamestaja().add(namestaj.get());
            porudzbinaDao.saveAndFlush(porudzbina.get());
            //return new ResponseEntity<Korpa>(porudzbina.get(), HttpStatus.CREATED);
        }else{
           // return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
