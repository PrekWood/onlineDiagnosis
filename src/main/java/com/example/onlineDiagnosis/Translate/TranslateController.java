package com.example.onlineDiagnosis.Translate;

import com.example.onlineDiagnosis.Model.ApiResponseTranslation;
import com.example.onlineDiagnosis.SupportedLanguages.SupportedLanguageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TranslateController {
    SupportedLanguageService supportedLanguageService;
    @CrossOrigin
    @GetMapping("api/translate/{toLang}?s={text}")
    public ResponseEntity<?> getTranslatedText(@PathVariable String toLang,
                                               @PathVariable String text) {
        if (supportedLanguageService.findSupportedLanguageByLanguageCode(toLang)==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(toLang+" doesn't supported");
        }
        String translatedText = ApiResponseTranslation.getTranslatedText(null,toLang,text)
                .getJSONObject("data").getString("pronunciation");
        if (translatedText == null) return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(translatedText);
    }
}
