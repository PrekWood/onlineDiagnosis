package com.example.onlineDiagnosis.SupportedLanguages;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
