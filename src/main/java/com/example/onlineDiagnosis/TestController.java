package com.example.onlineDiagnosis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {
    @CrossOrigin
    @GetMapping("test" )
    public ResponseEntity<?> uploadFile() {
        return ResponseEntity.status(HttpStatus.OK).body("test");
    }
}
