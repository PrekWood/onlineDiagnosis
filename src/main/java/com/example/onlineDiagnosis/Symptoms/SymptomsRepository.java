package com.example.onlineDiagnosis.Symptoms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomsRepository extends JpaRepository<Symptoms,Long> {
    Symptoms findAllById(int id);
}
