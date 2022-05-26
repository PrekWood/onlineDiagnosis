package com.example.onlineDiagnosis.OTP.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OtpValidationRequest {
    private String otpCode;
}
