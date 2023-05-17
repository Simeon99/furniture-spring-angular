package com.metropolitan.yokaappproject.config;

import com.metropolitan.yokaappproject.dao.KorisnikDao;
import com.metropolitan.yokaappproject.domain.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    KorisnikDao korisnikDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Korisnik> user = korisnikDao.findUsersByEmail(email);

        user.orElseThrow(()->new UsernameNotFoundException("Not found: "+ email));

        return user.map(MyUserDetails::new).get();
    }
}
