package com.minotor.desktop_minotor.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ApiService {
    private static final String BASE_URL = "http://localhost:8000/api";
    private static String jwtToken = null;

    // Setter pour stocker le token JWT reçu après login
    public static void setJwtToken(String token) {
        jwtToken = token;
    }

    // Getter pour récupérer le token JWT actuel
    public static String getJwtToken() {
        return jwtToken;
    }

    public static String get(String endpoint) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (jwtToken != null) {
            conn.setRequestProperty("Authorization", "Bearer " + jwtToken);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            return in.lines().collect(Collectors.joining());
        }
    }

    public static String post(String endpoint, String jsonData) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        if (jwtToken != null) {
            conn.setRequestProperty("Authorization", "Bearer " + jwtToken);
        }
        conn.setDoOutput(true);

        try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
            out.write(jsonData.getBytes(StandardCharsets.UTF_8));
            out.flush();
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            return in.lines().collect(Collectors.joining());
        }
    }

    public static String postVisit(String page, String visitedAt) throws IOException {
        String jsonData = String.format("{\"pageName\":\"%s\", \"visitDate\":\"%s\"}", page, visitedAt);
        return post("/visits", jsonData);
    }
}
