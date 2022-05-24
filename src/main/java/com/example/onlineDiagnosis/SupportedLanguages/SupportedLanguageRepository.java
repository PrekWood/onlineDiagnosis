package com.example.onlineDiagnosis.SupportedLanguages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportedLanguageRepository extends JpaRepository<SupportedLanguage,Long> {
    SupportedLanguage findSupportedLanguageByLanguageCode(String languageCode);
}
