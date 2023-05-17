package com.metropolitan.yokaappproject.controllers;

import com.metropolitan.yokaappproject.config.MyUserDetailsService;
import com.metropolitan.yokaappproject.dao.KorisnikDao;
import com.metropolitan.yokaappproject.domain.Dto;
import com.metropolitan.yokaappproject.domain.Korisnik;
import com.metropolitan.yokaappproject.domain.Namestaj;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/korisnik")
public class KorisnikControllerAppi {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private MyUserDetailsService myUserDetailsService;
    private KorisnikDao korisnikDao;

    public KorisnikControllerAppi(MyUserDetailsService myUserDetailsService, KorisnikDao korisnikDao) {
        this.myUserDetailsService = myUserDetailsService;
        this.korisnikDao = korisnikDao;
    }

    @GetMapping
    public ResponseEntity<List<Korisnik>> getAllMembers(){
        List<Korisnik> lista = (List<Korisnik>) korisnikDao.findAll();
        if(!lista.isEmpty())
            return new ResponseEntity<List<Korisnik>>(lista, HttpStatus.OK);
        else {
            return  new ResponseEntity<List<Korisnik>>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Korisnik> logIn(@RequestBody Dto dto){
        System.out.print("Person email was: ");
        System.out.println(dto.getEmail());
        System.out.print("Project pass was: ");
        System.out.println(dto.getPassword());
        List<Korisnik> lista = (List<Korisnik>) korisnikDao.findAll();

        for(int i = 0 ; i < lista.size(); i++){
            if(passwordEncoder.matches(dto.getPassword(), lista.get(i).getPassword())
                    &&dto.getEmail().equals(lista.get(i).getEmail())) {
                System.out.println("Ulogovani ste kao : " + dto.getPassword());

                return new ResponseEntity<Korisnik>(lista.get(i), HttpStatus.OK);
            }
        }

        return  new ResponseEntity<Korisnik>(HttpStatus.NOT_FOUND);

    }


    @PostMapping
    public ResponseEntity<Korisnik> create(@RequestBody Korisnik korisnik){
        Korisnik k = korisnik;
        korisnikDao.save(k);
        return new ResponseEntity<Korisnik>(korisnik, HttpStatus.CREATED);
    }

    @RequestMapping("{id}")
    public  ResponseEntity<Korisnik> delete(@PathVariable("id")Long id){
        Korisnik korisnik = korisnikDao.findById(id).get();
        if(korisnik!= null){
            korisnikDao.delete(korisnik);
            return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
        }
        else return new ResponseEntity<Korisnik>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Korisnik> edit(@PathVariable("id")Long id, @RequestBody  Korisnik korisnik){
        if (Objects.equals(id, korisnik.getIdKorinsika())) {
            korisnikDao.saveAndFlush(korisnik);
            return ResponseEntity.ok(korisnik);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<Korisnik> getById(@PathVariable("id") Long id){
        Korisnik korisnik = korisnikDao.findById(id).get();

        if(korisnik !=null){
            return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
        }
        return  new ResponseEntity<Korisnik>(HttpStatus.NOT_FOUND);
    }

}
