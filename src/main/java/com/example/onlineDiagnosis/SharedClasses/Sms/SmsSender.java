package com.example.onlineDiagnosis.SharedClasses.Sms;

import java.io.IOException;

public interface SmsSender {
    public void sendSms(String countryCodePrefix, String phoneNumber, String pinCode) throws IOException;
}
