package com.example.onlineDiagnosis.Diagnosis;

import com.example.onlineDiagnosis.Model.ApiResponseSymptomChecker;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@RestController
@AllArgsConstructor
public class DiagnosisController {
    public enum Gender{
        male, female
    }

    @PostMapping("/api/diagnosis/?gender={idGender}&birthday={birthday}&idSymptomsList={idSymptomsList}")
    @CrossOrigin
    public ResponseEntity<?> diagnosis(@PathVariable int[] idSymptomsList,
                                       @PathVariable int birthday,
                                       @PathVariable String idGender) {
        JSONArray bodySubLocations = ApiResponseSymptomChecker.getDiagnosis(idGender,birthday,idSymptomsList);
        if (bodySubLocations == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(bodySubLocations);
    }
    @GetMapping("/api/proposed-symptoms/?gender={idGender}&birthday={birthday}&idSymptomsList={idSymptomsList}")
    @CrossOrigin
    public ResponseEntity<?> proposedSymptoms(@PathVariable int[] idSymptomsList,
                                       @PathVariable int birthday,
                                       @PathVariable String idGender) {
        JSONArray bodySubLocations = ApiResponseSymptomChecker.getDiagnosis(idGender,birthday,idSymptomsList);
        if (bodySubLocations == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(bodySubLocations);
    }
    @PostMapping("/api/specialisation-based-on-diagnosis/?gender={idGender}&birthday={birthday}&idSymptomsList={idSymptomsList}")
    @CrossOrigin
    public ResponseEntity<?> specialisationBasedOnDiagnosis(@PathVariable int[] idSymptomsList,
                                       @PathVariable int birthday,
                                       @PathVariable String idGender) {
        JSONArray bodySubLocations = ApiResponseSymptomChecker.getSpecialisationsBasedOnDiagnosis(idSymptomsList,idGender,birthday);
        if (bodySubLocations == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(bodySubLocations);
    }
    @GetMapping("/api/specialisation-list/")
    @CrossOrigin
    public ResponseEntity<?> specialisationList() {
        JSONArray bodySubLocations = ApiResponseSymptomChecker.getSpecialisationsList();
        if (bodySubLocations == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(bodySubLocations);
    }
}
