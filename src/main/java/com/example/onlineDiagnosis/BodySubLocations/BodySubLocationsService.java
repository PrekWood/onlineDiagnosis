package com.example.onlineDiagnosis.BodySubLocations;

import com.example.onlineDiagnosis.BodyLocations.BodyLocation;
import com.example.onlineDiagnosis.BodyLocations.BodyLocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BodySubLocationsService {
    private final BodySubLocationsRepository repository;

    public BodySubLocations findById(int id) {
        return repository.findById(id);
    }

    public List<BodySubLocations> findAll(){
        return repository.findAll();
    }
    public void save(BodySubLocations b){
        repository.save(b);
    }
}
