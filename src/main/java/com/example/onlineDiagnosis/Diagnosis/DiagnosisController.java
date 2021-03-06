package com.example.onlineDiagnosis.Diagnosis;

import com.example.onlineDiagnosis.SharedClasses.ApiResponseSymptomChecker;
import com.example.onlineDiagnosis.SharedClasses.ResponseHandler;
import com.example.onlineDiagnosis.Symptoms.Symptoms;
import com.example.onlineDiagnosis.Symptoms.SymptomsService;
import com.example.onlineDiagnosis.User.User;
import com.example.onlineDiagnosis.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
public class DiagnosisController extends ResponseHandler {
    SymptomsService symptomsService;
    private final UserService userService;
    @PostMapping("/api/diagnosis/")
    @CrossOrigin
    public ResponseEntity<?> diagnosis() {
        User u = userService.loadUserFromJwt();
        ArrayList<Integer> userSymptomsIds = getUserSymptomsIdx(u);
        if (userSymptomsIds.isEmpty()) return createErrorResponse(HttpStatus.BAD_REQUEST,"User must choose at least one Symptom");
        //make the Call to the Api
        String response = ApiResponseSymptomChecker.getDiagnosisWithExtraInfo(
                u.getGender().toString(),
                Math.toIntExact(u.getYear()),
                userSymptomsIds);
        if (response == null) return createErrorResponse(HttpStatus.CONFLICT,"Api error");
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
    @PostMapping("/api/proposed-symptoms/")
    @CrossOrigin
    public ResponseEntity<?> proposedSymptoms() {
        User u = userService.loadUserFromJwt();
        ArrayList<Integer> userSymptomsIds = getUserSymptomsIdx(u);
        if (userSymptomsIds.isEmpty()) return createErrorResponse(HttpStatus.BAD_REQUEST,"User must choose at least one Symptom");
        String response = ApiResponseSymptomChecker.getDiagnosis(u.getGender().toString(), Math.toIntExact(u.getYear()),userSymptomsIds);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private ArrayList<Integer> getUserSymptomsIdx(User u) {
        // load user Symptoms from his "cart"
        ArrayList<Integer> userSymptomsIds= new ArrayList<>();
        for (Symptoms s:u.getSymptomsList()){
            userSymptomsIds.add((int) s.getId());
        }
        return userSymptomsIds;
    }

    @GetMapping("/api/specialisation-list/")
    @CrossOrigin
    public ResponseEntity<?> specialisationList() {
        String response = ApiResponseSymptomChecker.getSpecialisationsList();
        if (response == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}