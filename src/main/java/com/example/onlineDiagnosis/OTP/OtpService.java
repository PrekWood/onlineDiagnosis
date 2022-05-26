package com.example.onlineDiagnosis.OTP;

import com.example.onlineDiagnosis.User.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;

    public void save(Otp otp){
        otpRepository.save(otp);
    }

    public Otp getLastOtpOfUser(User user){
        return otpRepository.getLastOtpOfUser(user.getId());
    }
}
