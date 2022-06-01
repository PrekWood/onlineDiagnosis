package com.example.onlineDiagnosis.User.requests;

import com.example.onlineDiagnosis.User.emun.GENDER;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Long year;
    private GENDER gender;
}
