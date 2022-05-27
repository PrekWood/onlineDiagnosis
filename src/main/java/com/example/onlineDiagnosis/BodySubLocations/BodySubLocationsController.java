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

import java.util.HashMap;
import java.util.List;
@RestController
@AllArgsConstructor
public class BodySubLocationsController {
    BodySubLocationsService bodySubLocationsService;
    @CrossOrigin
    @GetMapping("/api/body-parts/{id}" )
    public ResponseEntity<?> getBodySubLocations(@PathVariable int id) {
        System.out.println(id);
        List<BodySubLocations> bodySubLocations = bodySubLocationsService.findByBodyLocationId(id);
        System.out.println(bodySubLocations.toString());

        if (bodySubLocations == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<>().put("error","Body Location id not Found"));
        return ResponseEntity.status(HttpStatus.OK).body(bodySubLocations);
    }
}
