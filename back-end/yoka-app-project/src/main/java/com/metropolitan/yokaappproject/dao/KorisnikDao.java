package com.metropolitan.yokaappproject.dao;

import com.metropolitan.yokaappproject.domain.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikDao extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findUsersByEmail(String email);
}
