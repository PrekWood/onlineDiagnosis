package com.example.onlineDiagnosis.Diagnosis;

import com.example.onlineDiagnosis.Diagnosis.Requests.DiagnosisRequests;
import com.example.onlineDiagnosis.Model.ApiResponseSymptomChecker;
import com.example.onlineDiagnosis.Model.ApiResponseTranslation;
import com.example.onlineDiagnosis.SharedClasses.ResponseHandler;
import com.example.onlineDiagnosis.SupportedLanguages.SupportedLanguageService;
import com.example.onlineDiagnosis.Symptoms.Symptoms;
import com.example.onlineDiagnosis.Symptoms.SymptomsService;
import com.example.onlineDiagnosis.User.User;
import com.example.onlineDiagnosis.User.UserService;
import com.example.onlineDiagnosis.User.emun.GENDER;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class DiagnosisController extends ResponseHandler {
    SymptomsService symptomsService;
    private final UserService userService;
    @PostMapping("/api/diagnosis/")
    @CrossOrigin
    public ResponseEntity<?> diagnosis() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("""
            [{"Issue":{"Accuracy":89.99999,"Ranking":1,"Description":{"DescriptionShort":"Mycosis is a fungal infection of animals and humans. Mycoses are common and its development can be assisted by a variety of physiological conditions. Mycoses start often on the skin (superficial) or in the lungs (systemic) and affect millions of people.","Synonyms":null,"TreatmentDescription":"The key for favorable results are early diagnosis, prompt use of appropriate antifungal treatment, and efforts to enhance the immune system. Antifungal drugs, usually creams, are prescribed to treat mycoses, except for example in the case of an infection of the nails and head. Most of the drugs are over-the-counter medicine. Some drugs can be administered intravenously for fungal infections that show resistance to other types of treatment. For fungal infections of the feet, keeping the feet dry is especially important to treatment.","Description":"Superficial mycosis is when the skin, mucous membranes, or nails are affected. If the fungus affects organs deeper in the body, then it is referred to as a systemic mycosis. Fungi that cause superficial mycosis are divided into three groups: dermatophytes, yeast, and molds. Molds usually only affect people with compromised immune systems. An infection by dermatophytes or yeast can occur in the context of diabetes or pregnancy. Yeasts colonize warm and moist areas of mucous membranes and skin folds. Skin folds, the mouth, genitals, and nail cuticles are susceptible to a fungal attack for this reason. Molds possess active components that can destroy callused human skin and eclusively attack the skin, hair, and nails. Fungi are found in soil, on animals, and can be transmitted between people.","PossibleSymptoms":"Discoloration of nails,Changes in the nails,Skin rash,Skin redness,Itching of skin,Dry skin,Flaking skin on the head,Flaking skin,Itching on head,Bold area among hair on the head,Dry mouth,Mouth pain,Brittleness of nails,Itching or burning in the genital area","ProfName":"Mycosis","MedicalCondition":"Depending on the location of the fungal attack, the skin may show different signs of infection. A yeast infection manifests as a reddish, inflamed area of skin surrounded by flaking. A fungal infection of the mucous membranes in the mouth can produce a white film that can be wiped away, with a felty feeling as well as troubles to taste. A fungal infection on the genitals is accompanied by itching and discharge in women and itchy, white nodes and blisters in men. An infection by mold produces disc-shaped foci that have an elevated, reddened, and flaky margin. If the infection is located between the toes, then the skin is white and chapped. An infection of the scalp with hair presents either on the entire scalp or in several areas as redness with dry flaking and hair loss. An attack on the nails can happen via small injuries or from circulation disorders and manifests as yellowing and thickening of the nails.","Name":"Fungal infection"},"ProfName":"Mycosis","IcdName":"Dermatophytosis;Other superficial mycoses;Candidiasis;Coccidioidomycosis;Histoplasmosis;Blastomycosis;Paracoccidioidomycosis;Sporotrichosis;Chromomycosis and phaeomycotic abscess;Aspergillosis;Cryptococcosis;Zygomycosis;Mycetoma;Other mycoses, not elsewhere classified;Unspecified mycosis","ID":144,"Icd":"B35;B36;B37;B38;B39;B40;B41;B42;B43;B44;B45;B46;B47;B48;B49","Name":"Fungal infection"},"doctors":[{"Accuracy":90,"Ranking":0,"ID":11,"Name":"Dermatology"},{"Accuracy":90,"Ranking":0,"ID":15,"Name":"General practice"},{"Accuracy":25.8750019,"Ranking":0,"ID":41,"Name":"Rheumatology"}]},{"Issue":{"Accuracy":36.31579,"Ranking":2,"Description":{"DescriptionShort":"Psoriasis is a disease that affects the skin, nails, and joints. It creates scaly, thickened skin usually on the knees, elbows, and scalp.","Synonyms":"Condition that produces plaques of thickened scaling skin","TreatmentDescription":"Treatment consists of decreasing inflammation of affected areas with anti-inflammatory drugs (cortisone, for example) and normalizing excessive thickening and hornification of the skin. Light therapy often contributes to the reduction of complaints. Many patients feel that their symptoms improve while vacationing in sunny locations.","Description":"Psoriasis is a disorder that usually affects the skin and nails, but can also affect the joints. Psoriasis produces scaly, whitish, thick plaques that may be large or small on the knees, elbows, and scalp. Joints may be affected with a form of arthritis.","PossibleSymptoms":"Changes in the nails,Discoloration of nails,Skin rash,Skin lesion,Dry skin,Skin redness,Itching on head,Flaking skin on the head,Joint pain,Bold area among hair on the head,Crusting","ProfName":"Psoriasis","MedicalCondition":"The cause behind psoriasis has not yet been explained, but it seems that genetic and environmental factors activate the immune system of the skin in such a way, that a localized heightened immune response occurs as well as an inflamed thickening of the top layers of skin. The disease is usually worse in youth than in adulthood. Patients often complain of itching and in certain cases it can manifest as a general feeling of illness or fever. Additionally, the highly visible lesions can become a mental burden for many patients.","Name":"Condition causing overproduction of skin cells"},"ProfName":"Psoriasis","IcdName":"Psoriasis","ID":26,"Icd":"L40","Name":"Condition causing overproduction of skin cells"},"doctors":[{"Accuracy":90,"Ranking":0,"ID":11,"Name":"Dermatology"},{"Accuracy":90,"Ranking":0,"ID":15,"Name":"General practice"},{"Accuracy":25.8750019,"Ranking":0,"ID":41,"Name":"Rheumatology"}]}]
        """);

//        User u = userService.loadUserFromJwt();
//        ArrayList<Integer> userSymptomsIds = getUserSymptomsIdx(u);
//        if (userSymptomsIds.isEmpty()) return createErrorResponse(HttpStatus.BAD_REQUEST,"User must choose at least one Symptom");
//        //make the Call to the Api
//        String response = ApiResponseSymptomChecker.getDiagnosisWithExtraInfo(
//                u.getGender().toString(),
//                Math.toIntExact(u.getYear()),
//                userSymptomsIds);
//        if (response == null) return createErrorResponse(HttpStatus.CONFLICT,"Api error");
//        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
    @PostMapping("/api/proposed-symptoms/")
    @CrossOrigin
    public ResponseEntity<?> proposedSymptoms() {
        User u = userService.loadUserFromJwt();
        ArrayList<Integer> userSymptomsIds = getUserSymptomsIdx(u);
        if (userSymptomsIds.isEmpty()) return createErrorResponse(HttpStatus.BAD_REQUEST,"User must choose at least one Symptom");
        String response = ApiResponseSymptomChecker.getDiagnosis(u.getGender().toString(), Math.toIntExact(u.getYear()),userSymptomsIds);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private ArrayList<Integer> getUserSymptomsIdx(User u) {
        // load user Symptoms from his "cart"
        ArrayList<Integer> userSymptomsIds= new ArrayList<>();
        for (Symptoms s:u.getSymptomsList()){
            userSymptomsIds.add((int) s.getId());
        }
        return userSymptomsIds;
    }

    @GetMapping("/api/specialisation-list/")
    @CrossOrigin
    public ResponseEntity<?> specialisationList() {
        String response = ApiResponseSymptomChecker.getSpecialisationsList();
        if (response == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}