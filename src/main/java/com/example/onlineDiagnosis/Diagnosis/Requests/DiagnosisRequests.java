package com.example.onlineDiagnosis.Diagnosis.Requests;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DiagnosisRequests {
    private int birthday;
    private String idGender;
}
