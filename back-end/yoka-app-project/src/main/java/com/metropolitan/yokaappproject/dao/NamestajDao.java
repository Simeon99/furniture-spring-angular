package com.metropolitan.yokaappproject.dao;

import com.metropolitan.yokaappproject.domain.Namestaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NamestajDao extends JpaRepository<Namestaj, Long> {
}
