package com.example.onlineDiagnosis.Controller;

import com.example.onlineDiagnosis.Model.ApiSymptomChecker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @CrossOrigin
    @GetMapping("/BodyLocation" )
    public ResponseEntity<?> getBodyLocations() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiSymptomChecker.getBodyLocation());
    }
    @CrossOrigin
    @GetMapping("/BodySubLocations" )
    public ResponseEntity<?> getBodySubLocations() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiSymptomChecker.getBodySubLocations(15));
    }
}
