package com.example.onlineDiagnosis.Model;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class SaveApiResponse {
    public boolean saveJsonStringToFile(String json, String fileName){
        try {
            JSONArray jsonArray = new JSONArray(json);
            File f = new File(fileName);
            String csv = CDL.toString(jsonArray);
            FileUtils.writeStringToFile(f,csv);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}

