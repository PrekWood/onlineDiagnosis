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
@RestController
@AllArgsConstructor
public class BodySubLocationsController extends ResponseHandler {
    BodySubLocationsService bodySubLocationsService;
    UserService userService;
    @CrossOrigin
    @GetMapping("/api/body-parts/{id}" )
    public ResponseEntity<?> getBodySubLocations(@PathVariable int id) {
        List<BodySubLocations> bodySubLocations = bodySubLocationsService.findByBodyLocationId(id);
        if (bodySubLocations == null) return createErrorResponse(HttpStatus.BAD_REQUEST,"Body Location id not Found");
        return createSuccessResponse(HttpStatus.OK,bodySubLocations);
    }
    @CrossOrigin
    @GetMapping("/api/body-parts/{id}/symptoms" )
    public ResponseEntity<?> getBodySubLocationsSymptoms(
            @PathVariable long id
    ) {

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("""
             [{"ID":152,"Name":"Hair loss","HasRedFlag":false,"HealthSymptomLocationIDs":[6,21],"ProfName":"","Synonyms":["Hair thinning","Thinning hair","Diffuse hair loss"]},{"ID":239,"Name":"Bold area among hair on the head","HasRedFlag":false,"HealthSymptomLocationIDs":[6,21],"ProfName":"","Synonyms":["Round patches of hair loss","Spot baldness","Circular hair loss"]},{"ID":245,"Name":"Flaking skin on the head","HasRedFlag":false,"HealthSymptomLocationIDs":[21],"ProfName":"","Synonyms":["Scaling of skin on the head"]},{"ID":247,"Name":"Itching on head","HasRedFlag":false,"HealthSymptomLocationIDs":[21,6],"ProfName":"","Synonyms":["Itching on the head","Itch on head","Itching on head","Pruritus"]},{"ID":269,"Name":"Scalp redness","HasRedFlag":false,"HealthSymptomLocationIDs":[6,21],"ProfName":"","Synonyms":[]}]        
        """);

//        User loggedInUser = userService.loadUserFromJwt();
//        if (loggedInUser == null) {
//            return createErrorResponse(HttpStatus.FORBIDDEN, "You are not logged in");
//        }
//        GENDER g = loggedInUser.getGender();
//        String response = ApiResponseSymptomChecker.getSymptomsInBodySubLocations((int) id,g.toString());
//        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
