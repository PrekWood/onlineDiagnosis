package com.example.onlineDiagnosis.SharedClasses.Security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTSecret {
    private static final String JWT_SECRET = "OAT#XKwcj!mloST^Yt2&9fItWFkc#Jk6O2CnzSj^v#9*srvjA4al5aVOH4!#c8gTLSq";

    public static String getJWTSecret(){
        return JWT_SECRET;
    }
}
