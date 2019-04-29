package com.example.jetpack._model.shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.jetpack._model.pojo.Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PreferencesManager {
    private static String TAG = PreferencesManager.class.getSimpleName();

    public static final String KEY_BASIC_AUTH = "key_basic_authentification";
    public static final String KEY_SESSION = "key_session";
    public static final String KEY_FCM_TOKEN = "key_fcm_token";

    private static String KEY = "couponsbussines";
    private static volatile PreferencesManager instance = null;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Context context;

    private PreferencesManager(Context context) {
        this.context = context;
    }

    public static void inititilize(Context context) {
        if (instance != null) {
            Log.e(TAG, "PreferenceManager is already initialized");
            return;
        }
        instance = new PreferencesManager(context);
        instance.init();
    }

    public static PreferencesManager getInstance() {
        if (instance == null) {
            try {
                throw new Exception("PreferenceManager is not initialized");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        return instance;
    }

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    public void saveSession(Session session) {
        initEditMode();
        Gson gson = new GsonBuilder().create();
        editor.putString(KEY_SESSION, gson.toJson(session));
        close();
    }

    public void saveKey(String key, String vale) {
        if (!key.isEmpty()) {
            initEditMode();
            editor.putString(key, vale);
            softClose();
        }
    }

    public String getKey(String key) {
        return prefs.getString(key, "");
    }

    public Session getSession() {
        if (!prefs.getString(KEY_SESSION, "").equals("")) {
            Gson gson = new GsonBuilder().create();
            String signupFlowJSON = prefs.getString(KEY_SESSION, "");
            return gson.fromJson(signupFlowJSON, Session.class);
        } else {
            return null;
        }
    }

    public void saveFcmToken(String fcmToken) {
        initEditMode();
        editor.putString(KEY_FCM_TOKEN, fcmToken);
        close();
    }

    public String getFcmToken() {
        if (!prefs.getString(KEY_FCM_TOKEN, "").equals("")) {
            String val = prefs.getString(KEY_FCM_TOKEN, "");
            return val;
        } else return null;
    }


    private void init() {
        prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    private void close() {
        editor.commit();
    }

    private void softClose() {
        editor.apply();
    }

    private void initEditMode() {
        editor = prefs.edit();
    }

    public void clean() {
        getSharedPreferences().edit().clear().apply();
    }
}