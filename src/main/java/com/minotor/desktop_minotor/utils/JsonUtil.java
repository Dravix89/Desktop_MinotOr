package com.minotor.desktop_minotor.utils;

import com.google.gson.Gson;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public JsonUtil() {
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return (T)gson.fromJson(json, clazz);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }
}
