package com.example.onlineDiagnosis.Controller;

import com.example.onlineDiagnosis.BodyLocations.BodyLocation;
import com.example.onlineDiagnosis.BodyLocations.BodyLocationService;
import com.example.onlineDiagnosis.BodySubLocations.BodySubLocations;
import com.example.onlineDiagnosis.BodySubLocations.BodySubLocationsService;
import com.example.onlineDiagnosis.Model.ApiSymptomChecker;
import com.example.onlineDiagnosis.Model.SaveApiResponse;
import com.example.onlineDiagnosis.Symptoms.Symptoms;
import com.example.onlineDiagnosis.Symptoms.SymptomsService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@RestController
@AllArgsConstructor
public class TestController {
    private final SaveApiResponse saveApiResponse;
    private final BodyLocationService bodyLocationService;
    private final BodySubLocationsService bodySubLocationsService;
    private final SymptomsService symptomsService;

    @CrossOrigin
    @GetMapping("/BodyLocation" )
    public ResponseEntity<?> getBodyLocations() {
        JSONArray response = ApiSymptomChecker.getBodyLocation();
        for (int i = 0; i < response.length(); i++) {
            JSONObject object = response.getJSONObject(i);
            BodyLocation b = new BodyLocation(object.getInt("ID"),object.getString("Name"));
            bodyLocationService.save(b);
        }
        //bodyLocationService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @CrossOrigin
    @GetMapping("/BodySubLocations" )
    public ResponseEntity<?> getBodySubLocations() {
        List<BodyLocation> bodyLocations = bodyLocationService.findAll();
        System.out.println(bodyLocations.size());
        for (BodyLocation b:bodyLocations){
            System.out.println("----------------------------");
            System.out.println(b.getId()+": "+b.getLocations());
            JSONArray response = ApiSymptomChecker.getBodySubLocations(b.getId());
            for (int i=0; i<response.length();i++){
                System.out.println("--BodySubLocations");
                JSONObject object = response.getJSONObject(i);
                BodySubLocations bodySubLocation = new BodySubLocations(object.getInt("ID"),object.getString("Name"));
                System.out.println(bodySubLocation.getId()+": "+ bodySubLocation.getName());
                bodySubLocationsService.save(bodySubLocation);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
    @CrossOrigin
    @GetMapping("/addEntity" )
    public ResponseEntity<?> loadFileDataFromCsv(){
        BodyLocation bl = new BodyLocation(5,"ασδ");
        bodyLocationService.save(bl);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
    @CrossOrigin
    @GetMapping("/readCsv" )
    public ResponseEntity<?> readCsv(){

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @CrossOrigin
    @GetMapping("/saveSymptoms" )
    public ResponseEntity<?> saveSymptoms(){
        JSONArray response = ApiSymptomChecker.getSymptoms(null);
        System.out.println(response.length());
        for (int i=0; i<response.length();i++){
            JSONObject object = response.getJSONObject(i);
            Symptoms symptoms = new Symptoms(object.getInt("ID"),object.getString("Name"));
            symptomsService.save(symptoms);
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }



}
