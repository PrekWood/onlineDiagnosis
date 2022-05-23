package com.example.onlineDiagnosis.BodyLocations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BodyLocationService {
    private final BodyLocationRepository repository;

    public List<BodyLocation> findAll(){
        return repository.findAll();
    }
    public void save(BodyLocation b){
        repository.save(b);
    }
}
