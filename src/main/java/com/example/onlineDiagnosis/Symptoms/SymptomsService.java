package com.example.onlineDiagnosis.Symptoms;

import com.example.onlineDiagnosis.BodySubLocations.BodySubLocations;
import com.example.onlineDiagnosis.BodySubLocations.BodySubLocationsRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SymptomsService {
    private final SymptomsRepository repository;
    public Symptoms getSymptomById(long id) throws ObjectNotFoundException{
        Optional<Symptoms> symptomsOptional = repository.findById(id);
        if(symptomsOptional.isEmpty()){
            throw new ObjectNotFoundException(Symptoms.class, "Symptom not found");
        }
        return symptomsOptional.get();
    }
    public List<Symptoms> findAll(){
        return repository.findAll();
    }
    public void save(Symptoms s){
        repository.save(s);
    }
}
