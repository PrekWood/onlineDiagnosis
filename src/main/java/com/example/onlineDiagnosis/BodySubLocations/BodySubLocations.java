package com.example.onlineDiagnosis.BodySubLocations;

import com.example.onlineDiagnosis.BodyLocations.BodyLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BodySubLocations {
    @Id
    private long id;
    private String name;
    private long bodyLocationId;
}
