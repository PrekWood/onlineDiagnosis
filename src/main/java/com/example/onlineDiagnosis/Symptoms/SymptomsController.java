package com.example.onlineDiagnosis.Symptoms;

import com.example.onlineDiagnosis.SharedClasses.ResponseHandler;
import com.example.onlineDiagnosis.User.User;
import com.example.onlineDiagnosis.User.UserService;
import com.example.onlineDiagnosis.User.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SymptomsController extends ResponseHandler {
    SymptomsService symptomsService;
    private final UserService userService;

    @CrossOrigin
    @PostMapping("/api/symptoms" )
    public ResponseEntity<?> addSymptom(@RequestBody Symptoms givenSymptom){

        User u = userService.loadUserFromJwt();
        List<Symptoms> symptomsList = u.getSymptomsList();

        if (givenSymptom.getId() == 0){
            return createErrorResponse("Id is 0");
        }
        for (Symptoms s:symptomsList){
            if (s.getId() == givenSymptom.getId()){
                return createErrorResponse(HttpStatus.CONFLICT,"Symptom already added");
            }
        }

        Symptoms symptom;
        try{
            symptom = symptomsService.getSymptomById(givenSymptom.getId());
        }catch (ObjectNotFoundException e){
            return createErrorResponse("Symptom could not be found");
        }
        symptomsList.add(symptom);
        return saveUserSymptomsList(u, symptomsList);
    }

    @CrossOrigin
    @GetMapping("/api/symptoms" )
    public ResponseEntity<?> getSymptoms(){
        User u = userService.loadUserFromJwt();
        List<Symptoms> symptoms = u.getSymptomsList();
        if (symptoms.isEmpty()) {
            return createSuccessResponse(HttpStatus.OK,"Your Symptom List is empty");
        }
        return createSuccessResponse(HttpStatus.OK,symptoms);
    }

    @CrossOrigin
    @DeleteMapping("/api/symptoms" )
    public ResponseEntity<?> deleteSymptom(@RequestBody Symptoms symptoms){
        User u = userService.loadUserFromJwt();
        List<Symptoms> symptomsList = u.getSymptomsList();
        for (Symptoms s:symptomsList){
            if (s.getId() == symptoms.getId()) {
                symptomsList.remove(s);
                return saveUserSymptomsList(u, symptomsList);
            }
        }
        return createErrorResponse("id not found in the list");
    }
    @CrossOrigin
    @DeleteMapping("/api/symptoms-all" )
    public ResponseEntity<?> deleteAllUserSymptoms(){
        User u = userService.loadUserFromJwt();
        List<Symptoms> symptomsList = u.getSymptomsList();
        symptomsList.clear();
        return saveUserSymptomsList(u, symptomsList);
    }

    @NotNull
    private ResponseEntity<?> saveUserSymptomsList(User u, List<Symptoms> symptomsList) {
        u.setSymptomsList(symptomsList);
        try {
            userService.updateUser(u);
        } catch (UserNotFoundException e) {
            return createErrorResponse(HttpStatus.FORBIDDEN, "Failed to update the User");
        }
        return createSuccessResponse(HttpStatus.OK,symptomsList);
    }

}