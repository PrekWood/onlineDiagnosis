package com.example.onlineDiagnosis.User.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OtpRequest {
    private String phoneNumber;
    private Long idCountryCode;
}
