package com.example.onlineDiagnosis.BodySubLocations;

import com.example.onlineDiagnosis.BodyLocations.BodyLocation;
import com.example.onlineDiagnosis.Model.ApiResponseSymptomChecker;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@AllArgsConstructor
public class BodySubLocationsController {
    BodySubLocationsService bodySubLocationsService;
    @CrossOrigin
    @GetMapping("/api/body-parts/{id}" )
    public ResponseEntity<?> getBodySubLocations(@PathVariable int id) {
        JSONArray bodySubLocations = ApiResponseSymptomChecker.getBodySubLocations(id);
        if (bodySubLocations == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(bodySubLocations);
    }
}
