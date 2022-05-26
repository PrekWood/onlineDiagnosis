package com.example.onlineDiagnosis.OTP;

import com.example.onlineDiagnosis.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String pinCode;
    private LocalDateTime dateAdd;

    public Otp(){
        this.dateAdd = LocalDateTime.now();
        this.pinCode = this.generatePinCode();
    }

    public String generatePinCode(){
        Random randomNumberGenerator = new Random();
        StringBuilder pinCodeBuilder = new StringBuilder();
        for(int randomNumberIndex=0; randomNumberIndex < 5; randomNumberIndex++){
            pinCodeBuilder.append(randomNumberGenerator.nextInt(10));
        }
        return pinCodeBuilder.toString();
    }


}
