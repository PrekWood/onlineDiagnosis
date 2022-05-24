package com.example.onlineDiagnosis.Model;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.sun.istack.NotNull;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
public class ApiResponseSymptomChecker {
    private static final OkHttpClient client = new OkHttpClient();
    public static final String API_SYMPTOM_CHECKER_KEY = "d8455cfac5mshb2e12524fc60827p13bf2fjsna477236d177e";
    public static final String API_SYMPTOM_CHECKER_HOST = "priaid-symptom-checker-v1.p.rapidapi.com";

    /**
     * Get Body Location
     * @return String JsonArray
     */
    public static JSONArray getBodyLocation(){
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/body/locations?language=en-gb")
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();
        return getResponse(request);
    }

    /**
     * Get SubLocations
     * @param idxBodyLocation Body sublocations can be called to receive all the body sub locations from a body location.
     *                        It returns an array of all body sublocations for the requested location_id.
     *                        Each element consists of the ID and Name.
     * @return Json String
     */
    public static JSONArray getBodySubLocations(int idxBodyLocation){
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/body/locations/"
                        +idxBodyLocation+
                        "?language=en-gb")
                .get()
                //host keys
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();
        return getResponse(request);
    }

    /**
     * Find Symptoms In Body Sub Locations
     * @param idxBodySubLocations see Body sublocations. If locationId = 0, then you get all symptoms
     * @param gender Options are man, woman, boy, girl.
     *                    The switch from Child to Adult is at the age of 11 (“edge year”).
     *                    This means that 12 and above is considered as Adult (for man, woman),
     *                    and 11 and below is considered as Child (for boy, girl)
     * @return String JsonArray
     */
    public static JSONArray getSymptomsInBodySubLocations(int idxBodySubLocations,String gender){
        if (!gender.equals("man") &&
                !gender.equals("woman") &&
                !gender.equals("boy") &&
                !gender.equals("girl")){
            gender = "man";
        }
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/symptoms/"
                        +idxBodySubLocations+
                        "/"+gender+
                        "?language=en-gb")
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        return getResponse(request);
    }

    /**
     * Get Specific symptom based ID_Symptom
     * With
     * @param idSymptoms Serialized array of selected symptom ids in json format. example symptoms=[234,11]
     *                    (JSON encoded int[] array)
     * @return
     */
    public static JSONArray getSymptoms(int[] idSymptoms){
        JSONArray jsonArray = new JSONArray();
        if (idSymptoms!=null){
            jsonArray = new JSONArray(idSymptoms);
        }

        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/symptoms?language=en-gb&format=json&" +
                        "symptoms="+jsonArray)
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        return getResponse(request);
    }

    /**
     * Issues can be either called to receive the full list of issues or a subset of issues
     * (e.g. all issues of a diagnosis).
     * @param idIssues JSON encoded int[] array Serialized array of selected issue ids in json format.
     *                 example issues=[234,235,236]
     * @return  String Json
     */
    public static JSONArray getIssues(int[] idIssues){
        JSONArray jsonArray = new JSONArray(idIssues);

        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/symptoms?language=en-gb&format=json&" +
                        "issues="+jsonArray)
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        return getResponse(request);
    }

    /**
     * Issue info can be called to receive all information about a health issue. The short description
     *  gives a short overview. A longer information can consist of
     * "Description", "MedicalCondition", "TreatmentDescription".
     * @param idIssue Number of the health issue
     * @return String Json Array
     */
    public static JSONArray getIssueInfo(@NotNull int idIssue){
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/issues/" +
                        idIssue +
                        "/info?language=en-gb")
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        return getResponse(request);
    }

    /**
     * The diagnosis is the core function of the symptom-checker to compute the potential health issues
     *  based on a set of symptoms, gender and age.
     * @param gender Options: male or female
     * @param birthday Year of birth of the patient.
     * @param idSymptoms Serialized array of selected symptom ids as a JSON encoded int[] array.
     *                   Example: [234,235,236]
     * @return String JsonArray
     */
    public static JSONArray getDiagnosis(@NotNull String gender,@NotNull int birthday,@NotNull int[] idSymptoms){
        JSONArray jsonArray = new JSONArray(idSymptoms);
        if (!gender.equals("male") &&
                !gender.equals("female")){
            gender = "male";
        }
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/diagnosis?" +
                        "gender="+gender+"&" +
                        "year_of_birth="+birthday+"&" +
                        "symptoms="+ jsonArray +
                        "language=en-gb")
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        return getResponse(request);
    }

    /**
     * The proposed symptoms can be called to request additional symptoms which
     *  can be related to the given symptoms in order to refine the diagnosis.
     * @param gender Male or female
     * @param birthday Year of birth
     * @param idSymptoms Serialized array of selected symptom ids in json format. example symptoms=[234,235,236]
     * @return
     */
    public static JSONArray getProposedDiagnosis(@NotNull String gender,@NotNull int birthday, int[] idSymptoms){
        JSONArray jsonArray = new JSONArray(idSymptoms);
        if (!gender.equals("male") &&
                !gender.equals("female")){
            gender = "male";
        }
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/symptoms/proposed?" +
                        "gender="+gender+"&" +
                        "year_of_birth="+birthday+"&" +
                        "language=en-gb&" +
                        "symptoms="+jsonArray)
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        return getResponse(request);
    }

    /**
     * The diagnosis is the core function of the symptom-checker to compute the potential health issues based on
     *      a set of symptoms, gender and age, but instead of getting computed diagnosis,
     *      you can also get list of suggested specialisations for calulated diseases
     * @param idSymptoms Serialized array of selected symptom ids in json format. example symptoms=[234,235,236]
     * @param gender male or female
     * @param birthday Year of birth
     * @return String JsonArray
     */
    public static JSONArray getSpecialisationsBasedOnDiagnosis(@NotNull int[] idSymptoms,@NotNull String gender,@NotNull int birthday){
        JSONArray jsonArray = new JSONArray(idSymptoms);
        if (!gender.equals("male") &&
                !gender.equals("female")){
            gender = "male";
        }
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/diagnosis/specialisations?" +
                        "symptoms="+jsonArray+"&" +
                        "gender="+gender+"&" +
                        "year_of_birth="+birthday+"&" +
                        "language=en-gb")
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        return getResponse(request);
    }

    /**
     * Check out our specialisations, so you can make your own mapping.
     * @return String JsonArray
     */
    public static JSONArray getSpecialisationsList(){
        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.rapidapi.com/specialisations?language=en-gb")
                .get()
                .addHeader("X-RapidAPI-Host", API_SYMPTOM_CHECKER_HOST)
                .addHeader("X-RapidAPI-Key", API_SYMPTOM_CHECKER_KEY)
                .build();

        return getResponse(request);
    }

    static JSONArray getResponse(Request request) {
        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()){
                return new JSONArray(response.body().string());
            }else {
                return null;
            }
        }catch (IOException ioException){
            ioException.printStackTrace();
            return null;
        }
    }
}
