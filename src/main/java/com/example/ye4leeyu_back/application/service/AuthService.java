package com.example.ye4leeyu_back.application.service;

import com.example.ye4leeyu_back.config.JwtUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final JwtUtil jwtUtil;

    @Value("${spring.oauth2.kakao.client-id}")
    private String kakaoClientId;
    @Value("${spring.oauth2.kakao.redirect-uri}")
    private String kakaoRedirectUri;
    @Value("${spring.oauth2.kakao.resource-uri}")
    String kakaoResourceUri;

    public String getKakaoAccessToken (String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(kakaoClientId);
            sb.append("&redirect_uri=").append(kakaoRedirectUri);
            sb.append("&code=").append(code);
            bw.write(sb.toString());
            bw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_Token;
    }

    private JsonNode getUserResource(String accessToken, String resourceUri) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class);
        return responseEntity.getBody();
    }

    public String getKakaoId(String accessToken) {
        JsonNode kakaoUserInfo = getUserResource(accessToken, kakaoResourceUri);
        return kakaoUserInfo.get("id").asText();
    }

    public String createJwtToken(String kakaoId) {
        return jwtUtil.createAccessToken(kakaoId, 1000 * 60 * 60 * 24 * 7);
    }
}
