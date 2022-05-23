package com.example.onlineDiagnosis.Symptoms;

import com.example.onlineDiagnosis.BodySubLocations.BodySubLocations;
import com.example.onlineDiagnosis.BodySubLocations.BodySubLocationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SymptomsService {
    private final SymptomsRepository repository;

    public List<Symptoms> findAll(){
        return repository.findAll();
    }
    public void save(Symptoms s){
        repository.save(s);
    }
}
