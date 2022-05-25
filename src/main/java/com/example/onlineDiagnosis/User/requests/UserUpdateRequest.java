package com.example.onlineDiagnosis.User.requests;

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
    private String phoneNumber;
}
