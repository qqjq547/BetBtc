package com.betbtc.app.tools;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.lang.reflect.Type;



public class GsonUtil {

    private static final Gson Gson = createGson(true);

    private static final Gson Gson_No_Nulls = createGson(false);

    private GsonUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Gson getGson() {
        return getGson(true);
    }

    public static Gson getGson(final boolean serializeNulls) {
        return serializeNulls ? Gson_No_Nulls : Gson;
    }
    public static String toJson(final Object object) {
        return toJson(object, true);
    }

    public static String toJson(final Object object, final boolean includeNulls) {
        return includeNulls ? Gson.toJson(object) : Gson_No_Nulls.toJson(object);
    }
    public static <T> T fromJson(final String json, final Class<T> type) {
        return Gson.fromJson(json, type);
    }

    public static <T> T fromJson(final String json, final Type type) {
        return Gson.fromJson(json, type);
    }
    public static <T> T fromJson(final Reader reader, final Class<T> type) {
        return Gson.fromJson(reader, type);
    }
    public static <T> T fromJson(final Reader reader, final Type type) {
        return Gson.fromJson(reader, type);
    }
    private static Gson createGson(final boolean serializeNulls) {
        final GsonBuilder builder = new GsonBuilder();
        if (serializeNulls) builder.serializeNulls();
        return builder.create();
    }

    public static <T> T copy(final T Object, final Class<T> type) {
        return Gson.fromJson(Gson.toJson(Object), type);
    }

    public static <T> T copy(final T Object, final Type type) {
        return Gson.fromJson(Gson.toJson(Object), type);
    }
}
