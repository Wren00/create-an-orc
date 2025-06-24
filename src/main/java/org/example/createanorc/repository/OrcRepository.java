package org.example.createanorc.repository;

import org.example.createanorc.models.Orc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface OrcRepository extends JpaRepository<Orc, Long> {

    Orc getById(Long id);

    ArrayList<Orc> getOrcsByUserId(Long userId);

    ArrayList<Orc> findAll();

}
