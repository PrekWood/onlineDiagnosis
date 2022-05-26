package com.example.onlineDiagnosis.Translate;

import com.example.onlineDiagnosis.Model.ApiResponseTranslation;
import com.example.onlineDiagnosis.Translate.TranslateRequest.TranslateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TranslateController {

    @CrossOrigin
    @PostMapping("api/Translate")
    public ResponseEntity<?> getTranslate(TranslateRequest translateRequest){
        if (translateRequest.getTl() == null || translateRequest.getText()==null) System.out.println("nullll");
        String translatedText = ApiResponseTranslation.getTranslatedText(
                        translateRequest.getTl(),
                        translateRequest.getText())
                .getJSONObject("data").getString("translation");
        return ResponseEntity.status(HttpStatus.OK).body(translatedText);
    }
}
