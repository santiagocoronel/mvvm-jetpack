package com.example.jetpack.util.cloudinary;

import android.content.Context;
import android.util.Log;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtils {

    public final static String CLOUDINARY_CLOUD_NAME = "zencom";
    public final static String CLOUDINARY_API_KEY = "918843621963771";
    public final static String CLOUDINARY_API_KEY_SECRET = "2T_m67NmTKfUdXUhQNXMIFjMBJk";

    public static void initCloudinary(Context context) {
        try {
            Map config = new HashMap();
            config.put("cloud_name", CloudinaryUtils.CLOUDINARY_CLOUD_NAME);
            config.put("api_key", CloudinaryUtils.CLOUDINARY_API_KEY);
            config.put("api_secret", CloudinaryUtils.CLOUDINARY_API_KEY_SECRET);
            MediaManager.init(context, config);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void uploadImageCloudinary(String pathImage,
                                             Context context,
                                             CloudinaryListener cloudinaryListener) {


        String requestId = MediaManager.get().upload(pathImage)
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.d(context.getClass().getSimpleName(), "onStart updaload CloudinaryUtils");
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Double progress = (double) bytes / totalBytes;
                        int val = ((int) Math.round(progress * 100));

                        cloudinaryListener.progress(val);
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        cloudinaryListener.onSuccess(String.valueOf(resultData.get("secure_url")));
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        cloudinaryListener.onError(error.getDescription());
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        cloudinaryListener.onReschedule(error.getDescription());
                    }

                })
                .dispatch();
    }
}
