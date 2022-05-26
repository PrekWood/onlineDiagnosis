package com.example.onlineDiagnosis.Symptoms;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Symptoms {
    @Id
    private long id;
    private String name;
}
