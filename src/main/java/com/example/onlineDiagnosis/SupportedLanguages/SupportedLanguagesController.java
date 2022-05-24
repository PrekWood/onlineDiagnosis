package com.example.onlineDiagnosis.SupportedLanguages;

import com.example.onlineDiagnosis.BodyLocations.BodyLocation;
import com.example.onlineDiagnosis.BodyLocations.BodyLocationService;
import com.example.onlineDiagnosis.BodySubLocations.BodySubLocationsService;
import com.example.onlineDiagnosis.Model.ApiResponseSymptomChecker;
import com.example.onlineDiagnosis.Model.ApiResponseTranslation;
import com.example.onlineDiagnosis.Model.SaveApiResponse;
import com.example.onlineDiagnosis.Symptoms.SymptomsService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class SupportedLanguagesController {
    private final SupportedLanguageService supportedLanguageService;

    @CrossOrigin
    @GetMapping("/getSupportedLanguages" )
    public ResponseEntity<?> getBodyLocations() {
        List<SupportedLanguage> supportedLanguages = supportedLanguageService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(supportedLanguages);
    }

}
