package com.example.onlineDiagnosis.SupportedLanguages;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupportedLanguageService {
    private final SupportedLanguageRepository repository;
    public List<SupportedLanguage> findAll(){
        return repository.findAll();
    }
    public SupportedLanguage findSupportedLanguageByLanguageCode(String supportedLanguage){
        return repository.findSupportedLanguageByLanguageCode(supportedLanguage);
    }
    public void save(SupportedLanguage sl){
        repository.save(sl);
    }
}
