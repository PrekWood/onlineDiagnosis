package com.example.onlineDiagnosis.BodyLocations;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BodyLocation {
    @Id
    private int id;
    private String locations;

}


/*
post /bodylocation create
put /bodylocation update
get /bodylocation info
delete /bodylocation delete
*/
