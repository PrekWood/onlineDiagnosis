package com.example.onlineDiagnosis.Issues;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class IssuesController {
    IssueService issueService;
    @GetMapping("/api/issues/")
    @CrossOrigin
    public ResponseEntity<?> getIssues() {
        List<Issue> issues = issueService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(issues);
    }
    @GetMapping("/api/issues/{idIssue}")
    @CrossOrigin
    public ResponseEntity<?> getIssuesInfo(@PathVariable List<Integer> idIssue) {
        List<Issue> issues = new ArrayList<>();
        for (Integer id:idIssue){
            Issue i = issueService.findById(id);
            if (i==null) return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            issues.add(i);
        }
        return ResponseEntity.status(HttpStatus.OK).body(issues);
    }

}
