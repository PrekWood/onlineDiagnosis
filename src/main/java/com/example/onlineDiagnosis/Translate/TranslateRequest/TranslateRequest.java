package com.example.onlineDiagnosis.Translate.TranslateRequest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TranslateRequest {
    private String tl;
    private String text;
}