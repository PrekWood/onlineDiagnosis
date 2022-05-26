package com.example.onlineDiagnosis.Diagnosis;

import com.example.onlineDiagnosis.Diagnosis.Requests.DiagnosisRequests;
import com.example.onlineDiagnosis.Model.ApiResponseSymptomChecker;
import com.example.onlineDiagnosis.Model.ApiResponseTranslation;
import com.example.onlineDiagnosis.SupportedLanguages.SupportedLanguageService;
import com.example.onlineDiagnosis.Symptoms.Symptoms;
import com.example.onlineDiagnosis.Symptoms.SymptomsService;
import com.example.onlineDiagnosis.User.UserService;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
public class DiagnosisController {
    SymptomsService symptomsService;
    private final UserService userService;
    public enum Gender{
        male, female
    }

    @PostMapping("/api/diagnosis/")
    @CrossOrigin
    public ResponseEntity<?> diagnosis(@RequestBody DiagnosisRequests diagnosisRequests) {
        if (
                diagnosisRequests.getBirthday() == 0 ||
                diagnosisRequests.getIdGender() == null || diagnosisRequests.getIdGender().equals("")
        ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body((new HashMap<>()).put("error", "Please fill in all the necessary fields"));
        }
        if (
                !diagnosisRequests.getIdGender().equals(Gender.female.name()) &&
                !diagnosisRequests.getIdGender().equals(Gender.male.name())
        ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body((new HashMap<>()).put("error", "Gender must be female or male"));
        }
        ArrayList<Integer> userSymptomsIds = getUserSymptomsIdx();
        if (userSymptomsIds.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body((new HashMap<>()).put("error", "User must choose at least one Symptom"));
//        make the Call to the Api
        JSONArray response = ApiResponseSymptomChecker.getDiagnosisWithExtraInfo(
                diagnosisRequests.getIdGender(),
                diagnosisRequests.getBirthday(),
                userSymptomsIds);
        if (response == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new HashMap<>()).put("error", "Api error"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/api/proposed-symptoms/?gender={idGender}&birthday={birthday}&idSymptomsList={idSymptomsList}")
    @CrossOrigin
    public ResponseEntity<?> proposedSymptoms(@PathVariable List<Integer> idSymptomsList,
                                       @PathVariable int birthday,
                                       @PathVariable String idGender) {
        ArrayList<Integer> userSymptomsIds = getUserSymptomsIdx();
        if (userSymptomsIds.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body((new HashMap<>()).put("error", "User must choose at least one Symptom"));

        JSONArray response = ApiResponseSymptomChecker.getDiagnosis(idGender,birthday,idSymptomsList);
        if (response == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<>().put("error","Api error"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private ArrayList<Integer> getUserSymptomsIdx() {
        //        load user Symptoms from his "cart"
        ArrayList<Integer> userSymptomsIds= new ArrayList<>();
        for (Symptoms s:userService.loadUserFromJwt().getSymptomsList()){
            userSymptomsIds.add((int) s.getId());
        }
        return userSymptomsIds;
    }

    @GetMapping("/api/specialisation-list/")
    @CrossOrigin
    public ResponseEntity<?> specialisationList() {
        JSONArray response = ApiResponseSymptomChecker.getSpecialisationsList();
        if (response == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}