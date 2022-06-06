package com.example.onlineDiagnosis.BodyLocations;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BodyLocationController {
    BodyLocationService bodyLocationService;
    @CrossOrigin
    @GetMapping("/api/body-parts" )
    public ResponseEntity<?> getBodyLocations() {
        List<BodyLocation> bodyLocations = bodyLocationService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bodyLocations);
    }
}
