package com.example.onlineDiagnosis.BodyLocations;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyLocationRepository extends JpaRepository<BodyLocation,Long> { //CrudRepository<BodyLocation,Long>
//    List<BodyLocationRepository> findAllByLocationsContains(String searchQuery, Sort s);
}

