package com.example.onlineDiagnosis.OTP;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    @Query(
        value = """
            SELECT *
            FROM otp
            WHERE user_id = ?1
            ORDER BY id DESC
            LIMIT 1;
        """,
        nativeQuery = true
    )
    Otp getLastOtpOfUser(Long idUser);
}
