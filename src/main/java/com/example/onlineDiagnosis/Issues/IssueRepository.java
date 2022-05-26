package com.example.onlineDiagnosis.Issues;

import com.example.onlineDiagnosis.BodySubLocations.BodySubLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {
    Issue findById(long id);
}
