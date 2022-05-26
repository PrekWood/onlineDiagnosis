package com.example.onlineDiagnosis.Translate;

import com.example.onlineDiagnosis.Model.ApiResponseTranslation;
import com.example.onlineDiagnosis.SharedClasses.ResponseHandler;
import com.example.onlineDiagnosis.Translate.TranslateRequest.TranslateRequest;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TranslateController extends ResponseHandler {

    @CrossOrigin
    @PostMapping("api/translate")
    public ResponseEntity<?> getTranslate(@RequestBody TranslateRequest translateRequest){


        if (translateRequest.getTl() == null || translateRequest.getText()==null) {
            return createErrorResponse("Please fill in all required fields");
        }

        try{
            String translatedText = ApiResponseTranslation.getTranslatedText(
                    translateRequest.getTl(),
                    translateRequest.getText()).getJSONObject("data").getString("translation");
            return ResponseEntity.status(HttpStatus.OK).body(translatedText);
        }catch (Exception e){
            return createErrorResponse("Something went wrong please try again");
        }

    }
}
