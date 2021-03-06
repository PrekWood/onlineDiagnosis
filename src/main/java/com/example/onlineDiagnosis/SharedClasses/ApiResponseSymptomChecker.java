package com.example.onlineDiagnosis.SharedClasses;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Api Calls in right order:
 *  0: getBodyLocation
 *  1: getBodySubLocations
 *  2: getSymptomsInBodySubLocations
 *  3: getDiagnosis
 *  4: getIssues
 *  5: getIssueInfo
 *  6: getSpecialisationsBasedOnDiagnosis
 *  7: getProposedDiagnosis
 *  8: getSpecialisationsList
 *
 */
@Component
@AllArgsConstructor
public class ApiResponseSymptomChecker {
    private static final OkHttpClient client = new OkHttpClient();
    public static final String API_SYMPTOM_CHECKER_KEY = "e97ab023b3msh48db599779468ccp18dd15jsn8a5d633bd715";
    public static final String API_SYMPTOM_CHECKER_HOST = "priaid-symptom-checker-v1.p.rapidapi.com";




    /**
     * Find Symptoms In Body Sub Locations
     *
     * @param idxBodySubLocations see Body sublocations. If locationId = 0, then you get all symptoms
     * @param gender              Options are man, woman, boy, girl.
     *                            The switch from Child to Adult is at the age of 11 (“edge year”).
     *                            This means that 12 and above is considered as Adult (for man, woman),
     *                            and 11 and below is considered as Child (for boy, girl)
     * @return String JsonArray
     */
    public static String getSymptomsInBodySubLocations(int idxBodySubLocations, String gender) {

        Request request = new Request.Builder()
                .url(
                    "https://priaid-symptom-checker-v1.p.rapidapi.com/symptoms/"
                    + idxBodySubLocations +
                    "/" + gender +
                    "?language=en-gb"
                )
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Issue info can be called to receive all information about a health issue. The short description
     * gives a short overview. A longer information can consist of
     * "Description", "MedicalCondition", "TreatmentDescription".
     *
     * @param idIssue Number of the health issue
     * @return String Json Array
     */
    public static JSONObject getIssueInfo(@NotNull int idIssue) {
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/issues/" +
                        idIssue +
                        "/info?language=en-gb")
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        return getJsonObjectResponse(request);
    }

    /**
     * The diagnosis is the core function of the symptom-checker to compute the potential health issues
     * based on a set of symptoms, gender and age.
     *
     * @param gender     Options: male or female
     * @param birthday   Year of birth of the patient.
     * @param idSymptoms Serialized array of selected symptom ids as a JSON encoded int[] array.
     *                   Example: [234,235,236]
     * @return String JsonArray
     */
    public static String getDiagnosis(@NotNull String gender, @NotNull int birthday, @NotNull List<Integer> idSymptoms) {
        if (!gender.equals("male") &&
                !gender.equals("female")) {
            gender = "male";
        }
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/diagnosis?" +
                        "gender="+gender+"&" +
                        "year_of_birth="+birthday+"&" +
                        "symptoms=" + idSymptoms+ "&"+
                        "language=en-gb")
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The diagnosis is the core function of the symptom-checker to compute the potential health issues based on
     * a set of symptoms, gender and age, but instead of getting computed diagnosis,
     * you can also get list of suggested specialisations for calulated diseases
     *
     * @param idSymptoms Serialized array of selected symptom ids in json format. example symptoms=[234,235,236]
     * @param gender     male or female
     * @param birthday   Year of birth
     * @return String JsonArray
     */
    public static JSONArray getSpecialisationsBasedOnDiagnosis(@NotNull List<Integer> idSymptoms, @NotNull String gender, @NotNull int birthday) {
        if (!gender.equals("male") &&
                !gender.equals("female")) {
            gender = "male";
        }
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/diagnosis/specialisations?" +
                        "symptoms=" + idSymptoms + "&" +
                        "gender=" + gender + "&" +
                        "year_of_birth=" + birthday + "&" +
                        "language=en-gb")
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        return getJsonArrayResponse(request);
    }

    /**
     * Check out our specialisations, so you can make your own mapping.
     *
     * @return String JsonArray
     */
    public static String getSpecialisationsList() {
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/specialisations?language=en-gb")
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (IOException ignore){

        }
        return null;
    }


    public static String getDiagnosisWithExtraInfo(String gender, int birthday, List<Integer> idSymptoms) {
        String response = getDiagnosis(gender, birthday, idSymptoms);
        JSONArray jsonArrayResponse = new JSONArray(response);
        JSONArray newResponse = new JSONArray();
        try {
            for (int i = 0; i < jsonArrayResponse.length(); i++) {
                JSONObject diagnosis = jsonArrayResponse.getJSONObject(i);
                JSONObject issue = diagnosis.getJSONObject("Issue");
                issue.put("Description",getIssueInfo(issue.getInt("ID")));
                diagnosis.remove("Specialisation");
                diagnosis.put("doctors",getSpecialisationsBasedOnDiagnosis(idSymptoms,gender,birthday));
                newResponse.put(diagnosis);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return newResponse.toString();
    }
    static JSONArray getJsonArrayResponse(Request request) {
        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return new JSONArray(response.body().string());
            } else {
                return null;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }
    static JSONObject getJsonObjectResponse(Request request) {
        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return new JSONObject(response.body().string());
            } else {
                System.out.println(response.code()+" "+response.message());;
                return null;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

}