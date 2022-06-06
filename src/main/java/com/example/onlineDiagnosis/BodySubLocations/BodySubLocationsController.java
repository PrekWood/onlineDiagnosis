package com.example.onlineDiagnosis.BodySubLocations;

import com.example.onlineDiagnosis.SharedClasses.ApiResponseSymptomChecker;
import com.example.onlineDiagnosis.SharedClasses.ResponseHandler;
import com.example.onlineDiagnosis.User.User;
import com.example.onlineDiagnosis.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        String response = ApiResponseSymptomChecker.getSymptomsInBodySubLocations(
            (int) id,
            userService.genderToStringMan(loggedInUser.getGender())
        );
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
