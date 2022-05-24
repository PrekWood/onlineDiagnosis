package com.example.onlineDiagnosis.Symptoms;

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
public class SymptomsController {
    SymptomsService symptomsService;
    @CrossOrigin
    @GetMapping("/api/symptoms{id}" )
    public ResponseEntity<?> saveSymptoms(@PathVariable int[] id){

        List<Symptoms> symptoms = symptomsService.getSymptomsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(symptoms);
    }
}
