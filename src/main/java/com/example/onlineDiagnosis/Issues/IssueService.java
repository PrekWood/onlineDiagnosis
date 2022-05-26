package com.example.onlineDiagnosis.Issues;

import com.example.onlineDiagnosis.BodySubLocations.BodySubLocations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IssueService {
    private final IssueRepository repository;
    public Issue findById(int id) {
        return repository.findById(id);
    }
    public List<Issue> findAll(){return repository.findAll();}
    public void save(Issue issue){repository.save(issue);}
}
