package com.example.onlineDiagnosis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class OnlineDiagnosisApplicationTests {

	JSONArray jsonArray = new JSONArray();
	JSONObject jsonObject = new JSONObject();
	@Test
	void contextLoads() throws JSONException {
		int[] id = new int[2];
		id[0]=234;
		id[1]=11;

		System.out.println(id.toString());
		String response;
		try {
			response = URLEncoder.encode(id.toString(), StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		System.out.println(response);

		jsonArray.put(id);
		System.out.println(jsonArray.toString());
//		%5B234%2C11%5D
	}

}
