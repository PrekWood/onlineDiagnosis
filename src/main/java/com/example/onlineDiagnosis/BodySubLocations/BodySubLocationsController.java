package com.example.onlineDiagnosis.BodySubLocations;

import com.example.onlineDiagnosis.BodyLocations.BodyLocation;
import com.example.onlineDiagnosis.Model.ApiResponseSymptomChecker;
import com.example.onlineDiagnosis.SharedClasses.ResponseHandler;
import com.example.onlineDiagnosis.User.User;
import com.example.onlineDiagnosis.User.UserService;
import com.example.onlineDiagnosis.User.emun.GENDER;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class BodySubLocationsController extends ResponseHandler {
    BodySubLocationsService bodySubLocationsService;
    UserService userService;
    @CrossOrigin
    @GetMapping("/api/body-parts/{id}" )
    public ResponseEntity<?> getBodySubLocations(@PathVariable int id){
        List<BodySubLocations> bodySubLocations = bodySubLocationsService.findByBodyLocationId(id);
        if (bodySubLocations == null) return createErrorResponse(HttpStatus.BAD_REQUEST,"Body Location id not Found");
        return createSuccessResponse(HttpStatus.OK,bodySubLocations);
    }

    @CrossOrigin
    @GetMapping("/api/body-parts/{id}/symptoms" )
    public ResponseEntity<?> getBodySubLocationsSymptoms(
            @PathVariable long id
    ) {
        User loggedInUser = userService.loadUserFromJwt();
        if (loggedInUser == null) {
            return createErrorResponse(HttpStatus.FORBIDDEN, "You are not logged in");
        }
        GENDER g = loggedInUser.getGender();
        String response = ApiResponseSymptomChecker.getSymptomsInBodySubLocations((int) id,g.toString());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
