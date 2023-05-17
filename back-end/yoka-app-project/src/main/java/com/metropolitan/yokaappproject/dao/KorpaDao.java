package com.metropolitan.yokaappproject.dao;

import com.metropolitan.yokaappproject.domain.Korpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorpaDao extends JpaRepository<Korpa, Long> {
}
