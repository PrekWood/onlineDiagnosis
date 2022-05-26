package com.example.onlineDiagnosis.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import com.sun.istack.NotNull;
import okhttp3.MultipartBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class ApiResponseTranslation{
    private static final OkHttpClient client = new OkHttpClient();

    public static final String API_SYMPTOM_CHECKER_KEY = "d8455cfac5mshb2e12524fc60827p13bf2fjsna477236d177e";
    public static final String API_GOOGLE_TRANSLATE_HOST = "google-translate20.p.rapidapi.com";
    public static final String API_GOOGLE_CONTENT_TYPE = "application/x-www-form-urlencoded";

    public static JSONObject getTranslatedText(String targetLanguageCode,String text){
        Request request = new Request.Builder()
                .url("https://google-translate20.p.rapidapi.com/translate?" +
                        "tl="+targetLanguageCode+
                        "&text="+text)
                .get()
                .addHeader("X-RapidAPI-Host", API_GOOGLE_TRANSLATE_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .addHeader("content-type", API_GOOGLE_CONTENT_TYPE)
                .build();
        return getResponse(request);
    }

    public static String getTranslatedTextGoogleCloud(String targetLanguageCode,String text){
        String googleCloudAPIKey = ""; //ρωτα τον νικο";

        /**
         * {
         *   "q": "LOG OUT",
         *   "source": "en",
         *   "target": "es",
         *   "format": "text"
         * }
         */
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                "{" +
                "'q': '"+text+"',"+
                "'target': '"+targetLanguageCode+"',"+
                "'format': 'text',"+
                "}"
        );

        Request request = new Request.Builder()
                .url("https://translation.googleapis.com/language/translate/v2?key="+googleCloudAPIKey)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                return null;
            }

            return response.body().string();
        }catch (IOException ioException){
            ioException.printStackTrace();
            return null;
        }

    }

    public static JSONObject getSupportedLanguages(){
        Request request = new Request.Builder()
                .url("https://google-translate20.p.rapidapi.com/languages")
                .get()
                .addHeader("X-RapidAPI-Host", API_GOOGLE_TRANSLATE_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();
        return getResponse(request);
    }
    static JSONObject getResponse(Request request) {
        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()){
                return new JSONObject(response.body().string());
            }else {
                return null;
            }
        }catch (IOException ioException){
            ioException.printStackTrace();
            return null;
        }
    }

}
