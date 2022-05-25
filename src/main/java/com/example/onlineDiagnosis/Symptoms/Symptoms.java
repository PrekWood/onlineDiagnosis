package com.example.onlineDiagnosis.Symptoms;

import com.example.onlineDiagnosis.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Symptoms {
    @Id
    private int id;
    @ManyToOne
    private User user;
    private String name;
}
