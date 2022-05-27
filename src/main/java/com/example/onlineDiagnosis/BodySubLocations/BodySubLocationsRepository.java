package com.example.onlineDiagnosis.BodySubLocations;

import com.example.onlineDiagnosis.BodyLocations.BodyLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodySubLocationsRepository extends JpaRepository<BodySubLocations,Long> {
    List<BodySubLocations> findAllByBodyLocationId(long id);
}
