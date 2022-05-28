package com.example.onlineDiagnosis.User.requests;

import com.example.onlineDiagnosis.User.emun.GENDER;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long year;
    private GENDER gender;
}
