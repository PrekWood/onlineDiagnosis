package com.example.onlineDiagnosis.Translate;

import com.example.onlineDiagnosis.SharedClasses.ApiResponseTranslation;
import com.example.onlineDiagnosis.SharedClasses.ResponseHandler;
import com.example.onlineDiagnosis.Translate.TranslateRequest.TranslateRequest;
import com.example.onlineDiagnosis.User.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

@RestController
@AllArgsConstructor
public class TranslateController extends ResponseHandler {

    private UserService userService;

    @CrossOrigin
    @PostMapping("api/translate")
    public ResponseEntity<?> getTranslate(@RequestBody TranslateRequest translateRequest){


        if (translateRequest.getTl() == null || translateRequest.getText()==null) {
            return createErrorResponse("Please fill in all required fields");
        }

        try{
//            String translatedText = ApiResponseTranslation.getTranslatedText(
            String translatedText = ApiResponseTranslation.getTranslatedTextGoogleCloud(
                    translateRequest.getTl(),
                    translateRequest.getText());//.getJSONObject("data").getString("translation");
            return ResponseEntity.status(HttpStatus.OK).body(translatedText);
        }catch (Exception e){
            System.out.println(e);
            return createErrorResponse("Something went wrong please try again");
        }

    }
    @CrossOrigin
    @GetMapping("api/languages")
    public ResponseEntity<?> getAcceptedLanguages(){

        // Get languages from src/main/resources/languages.json
        List<HashMap<String, String>> availableLanguages = null;
        try {
            File file = ResourceUtils.getFile("classpath:languages.json");
            Scanner myReader = new Scanner(file);

            // load data as a string
            StringBuilder availableLanguagesStringBuilder = new StringBuilder();
            while (myReader.hasNextLine()) {
                availableLanguagesStringBuilder.append(myReader.nextLine());
            }
            myReader.close();

            // json parse
            availableLanguages = new ObjectMapper().readValue(
                    availableLanguagesStringBuilder.toString(),
                    List.class
            );

        } catch (IOException e) {
            return createErrorResponse("Error while loading the available languages");
        }


        return createSuccessResponse(availableLanguages);

    }

}
