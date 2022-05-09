package com.example.onlineDiagnosis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.onlineDiagnosis.Controller"})
public class OnlineDiagnosisApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineDiagnosisApplication.class, args);
	}

}
