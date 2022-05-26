package com.example.onlineDiagnosis.OTP;

import com.example.onlineDiagnosis.OTP.requests.OtpRequest;
import com.example.onlineDiagnosis.OTP.requests.OtpValidationRequest;
import com.example.onlineDiagnosis.SharedClasses.ResponseHandler;
import com.example.onlineDiagnosis.SharedClasses.Sms.SmsSender;
import com.example.onlineDiagnosis.SharedClasses.Sms.Twilio;
import com.example.onlineDiagnosis.User.User;
import com.example.onlineDiagnosis.User.UserService;
import com.example.onlineDiagnosis.User.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/" )
public class OtpController extends ResponseHandler {

    private final UserService userService;
    private final OtpService otpService;

    @CrossOrigin
    @PostMapping("otp" )
    public ResponseEntity<?> sendOtp(@RequestBody OtpRequest request) {

        String phoneNumber = request.getPhoneNumber();

        // Phone number validation
        if (phoneNumber.equals("") || phoneNumber.length() != 10) {
            return createErrorResponse("Invalid phone number");
        }

        // Search for user
        User loggedInUser = userService.loadUserFromJwt();
        if (loggedInUser == null) {
            return createErrorResponse(HttpStatus.FORBIDDEN, "You are not logged in");
        }

        // update fields
        loggedInUser.setPhoneNumber(phoneNumber);
        try {
            userService.updateUser(loggedInUser);
        } catch (UserNotFoundException e) {
            return createErrorResponse("User not found while updating");
        }

        // Generate Otp
        Otp registerOtp = new Otp();
        registerOtp.setUser(loggedInUser);
        otpService.save(registerOtp);

        // Send sms
        try {
            SmsSender smsSender = new Twilio();
            smsSender.sendSms("30", phoneNumber, registerOtp.getPinCode());
        } catch (IOException e) {
            return createErrorResponse("SMS Could not be sent");
        }

        return createSuccessResponse();
    }

    @PostMapping("otp/resend" )
    public ResponseEntity<?> resendOtp() {

        // Search for user
        User loggedInUser = userService.loadUserFromJwt();
        if (loggedInUser == null) {
            return createErrorResponse(HttpStatus.FORBIDDEN, "You are not loged in");
        }

        // Generate Otp
        Otp registerOtp = new Otp();
        registerOtp.setUser(loggedInUser);
        otpService.save(registerOtp);

        // Send sms
        try {
            SmsSender smsSender = new Twilio();
            smsSender.sendSms(
                    "30",
                    loggedInUser.getPhoneNumber(),
                    registerOtp.getPinCode()
            );
        } catch (IOException e) {
            return createErrorResponse("SMS Could not be sent");
        }

        return createSuccessResponse();
    }


    @PostMapping("otp/validate" )
    public ResponseEntity<?> validateOtp(@RequestBody OtpValidationRequest request) {

        String otpCode = request.getOtpCode();

        // Phone number validation
        if (otpCode.equals("" ) || otpCode.length() != 5) {
            return createErrorResponse("Invalid PIN");
        }

        // Search for user
        User loggedInUser = userService.loadUserFromJwt();
        if (loggedInUser == null) {
            return createErrorResponse(HttpStatus.FORBIDDEN, "You are not loged in");
        }

        // Get last otp and compare it with the user's
        Otp lastOtp = otpService.getLastOtpOfUser(loggedInUser);
        if (!lastOtp.getPinCode().equals(otpCode)) {
            return createErrorResponse("Invalid PIN");
        }

        // Set user as validated
        loggedInUser.setPhoneValidated(true);
        try {
            userService.updateUser(loggedInUser);
        } catch (UserNotFoundException e) {
            return createErrorResponse("Error while updating the user");
        }

        return createSuccessResponse();
    }

}
