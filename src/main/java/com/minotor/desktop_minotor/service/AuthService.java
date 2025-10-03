package com.minotor.desktop_minotor.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.json.JSONObject;

public class AuthService {

    private static String token = null;

    public static boolean login(String username, String password) {
        try {
            URL url = new URL("http://localhost:8000/api/login_check");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);


            JSONObject jsonInput = new JSONObject();
            jsonInput.put("username", username);
            jsonInput.put("password", password);


            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int code = conn.getResponseCode();
            System.out.println( conn.getRequestMethod());

            if (code == 200 || code == 307) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    token = jsonResponse.getString("token");

                    ApiService.setJwtToken(token);

                    return true;
                }

            } else {
                System.out.println("Erreur login : code " + code);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getToken() {
        return token;
    }
}

