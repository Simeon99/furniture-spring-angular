package com.metropolitan.yokaappproject.dao;

import com.metropolitan.yokaappproject.domain.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorudzbinaDao extends JpaRepository<Porudzbina, Long> {
}
