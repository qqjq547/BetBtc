package com.betbtc.app.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import androidx.annotation.NonNull;

import com.betbtc.app.model.UserInfo;

import java.lang.reflect.Type;


public class PrefUtil {
    public static final String USER_ROLE = "user_role";
    public static final String MOBILE = "mobile";
    private SharedPreferences sharedPreference;
    private static PrefUtil preference = null;
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String USER_TOKEN = "userToken";
    public static final String USER_INFO = "userToken";


    public static void init(Context context) {
        preference = new PrefUtil(context);
    }

    public static synchronized PrefUtil getInstance() {
        return preference;
    }

    private PrefUtil(Context context) {
        String packageName = context.getPackageName() + "_preferences";
        sharedPreference = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
    }

    public String getLatitude() {
        return sharedPreference.getString(LATITUDE, "");
    }

    public String getLongitude() {
        return sharedPreference.getString(LONGITUDE, "");
    }

    public void clean() {
        Editor editor = sharedPreference.edit();
        editor.clear();
        editor.commit();
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        boolean tid = sharedPreference.getBoolean(name, defaultValue);
        return tid;
    }

    public int getInt(String name, int defaultValue) {
        int tid = sharedPreference.getInt(name, defaultValue);
        return tid;
    }

    public long getLong(String name, long defaultValue) {
        long tid = sharedPreference.getLong(name, defaultValue);
        return tid;
    }

    public String getString(String name, String defaultValue) {
        String tid = sharedPreference.getString(name, defaultValue);
        return tid;
    }

    public float getFloat(String name, float defaultValue) {
        float tid = sharedPreference.getFloat(name, defaultValue);
        return tid;
    }

    public void setInt(String name, int value) {
        Editor edit = sharedPreference.edit();
        edit.putInt(name, value);
        edit.commit();
    }

    public void setLong(String name, long value) {
        Editor edit = sharedPreference.edit();
        edit.putLong(name, value);
        edit.commit();
    }

    public void setBoolean(String name, boolean value) {
        Editor edit = sharedPreference.edit();
        edit.putBoolean(name, value);
        edit.commit();
    }

    public void setString(String name, String value) {
        Editor edit = sharedPreference.edit();
        edit.putString(name, value);
        edit.commit();
    }

    public void setFloat(String name, float value) {
        Editor edit = sharedPreference.edit();
        edit.putFloat(name, value);
        edit.commit();
    }

    public String getUserToken() {
        return getString(USER_TOKEN, "");
    }
    public UserInfo getUserInfo() {
        return getEntity(USER_INFO, UserInfo.class);
    }

    public void put(@NonNull final String key, final Object javaBean) {
        put(key, javaBean, false);
    }

    public void put(@NonNull final String key, final Object javaBean, boolean isCommit) {
        Editor edit = sharedPreference.edit();
        if (isCommit) {
            edit.putString(key, GsonUtil.toJson(javaBean)).commit();
        } else {
            edit.putString(key, GsonUtil.toJson(javaBean)).apply();
        }
    }
    public <T extends Object> T getEntity(@NonNull final String key, final Class<T> tClass) {
        String value = getString(key, "");
        return GsonUtil.fromJson(value, tClass);
    }

    public <T extends Object> T getEntity(@NonNull final String key, final Class<T> tClass, final String defaultValue) {
        String value = getString(key, defaultValue);
        return GsonUtil.fromJson(value, tClass);
    }

    public <T extends Object> T getEntity(@NonNull final String key, Type typeOfT) {
        String value = getString(key, "");
        return GsonUtil.fromJson(value, typeOfT);
    }




}
