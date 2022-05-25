package com.example.onlineDiagnosis.Issues;

import com.example.onlineDiagnosis.Model.ApiResponseSymptomChecker;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class IssuesController {
    @GetMapping("/api/issues/")
    @CrossOrigin
    public ResponseEntity<?> getIssues() {
        JSONArray bodySubLocations = ApiResponseSymptomChecker.getIssues(null);
        if (bodySubLocations == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(bodySubLocations);
    }
    @GetMapping("/api/issues/{idIssue}")
    @CrossOrigin
    public ResponseEntity<?> getIssuesInfo(@PathVariable int[] idIssue) {
        JSONArray bodySubLocations = ApiResponseSymptomChecker.getIssues(idIssue);
        if (bodySubLocations == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(bodySubLocations);
    }

}
