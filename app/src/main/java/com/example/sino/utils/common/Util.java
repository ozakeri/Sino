package com.example.sino.utils.common;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.sino.R;
import com.example.sino.SinoApplication;
import com.example.sino.model.Device;
import com.example.sino.model.RequestBaseBean;
import com.example.sino.model.db.ChatMessage;
import com.example.sino.model.db.TmpChatMessage;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.InetAddress;
import java.util.ArrayList;

public class Util {


    public static class WSParameter {
        public String key;
        public Object value;

        public WSParameter(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    public static String createJson(ArrayList<WSParameter> wsParameters, TmpChatMessage chatMessage) {

        System.out.println("chatMessage===" + chatMessage);

        RequestBaseBean requestBaseBean = new RequestBaseBean();
        requestBaseBean.setTmpChatMessage(chatMessage);
        JsonElement jsonElement = new Gson().toJsonTree(requestBaseBean);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        for (WSParameter wsParameter : wsParameters)
            jsonObject.addProperty(wsParameter.key, wsParameter.value + "");
        String json = jsonObject.toString();
        //json = json.replaceAll("\\\\", "");
        System.out.println("createJson===" + json);

        return json;
    }

    public static String createChatMessageJson(ArrayList<WSParameter> wsParameters) {

        JsonObject jsonObject = new JsonObject();
        for (WSParameter wsParameter : wsParameters)
            jsonObject.addProperty(wsParameter.key, wsParameter.value + "");
        String json = jsonObject.toString();
        System.out.println("createJson===" + json);
        return json;

    }


    public static Device getDevice() {

        return new Device(getImei(SinoApplication.getInstance().getApplicationContext()),
                getDeviceName(),
                "Android",
                getOSVersion());
    }

    @SuppressLint("HardwareIds")
    public static String getImei(Context context) {
        String imei = null;
        if (context != null) {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                    imei = android.provider.Settings.Secure.getString(
                            context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
                    System.out.println("imei===111===" + imei);

                } else {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    imei = telephonyManager.getDeviceId();
                }
            }
        }
        return imei;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public static String getOSVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return release + " (API: " + sdkVersion + ")";
    }


    public static void showProgress(CircularProgressView progressView) {
        progressView.setVisibility(View.VISIBLE);
    }

    public static void hideProgress(CircularProgressView progressView) {
        progressView.setVisibility(View.GONE);
    }

    @SuppressLint("ResourceAsColor")
    public static Toast showToast(Toast toast, Context context) {
        Typeface typeface = Typeface.create("BYekan.ttf", Typeface.BOLD);
        toast.setGravity(Gravity.CENTER, 0, 0);

        View view = LayoutInflater.from(context)
                .inflate(R.layout.toast_layout, null);

        TextView messageTextView = view.findViewById(R.id.tvMessage);
        messageTextView.setTextSize(15);
        messageTextView.setTextColor(R.color.mdtp_light_gray);
        messageTextView.setTypeface(typeface);
        return toast;
    }


    public static String farsiNumberReplacement(String text) {
        text = text.replaceAll("۰", "0");
        text = text.replaceAll("۱", "1");
        text = text.replaceAll("۲", "2");
        text = text.replaceAll("۳", "3");
        text = text.replaceAll("۴", "4");
        text = text.replaceAll("۵", "5");
        text = text.replaceAll("۶", "6");
        text = text.replaceAll("۷", "7");
        text = text.replaceAll("۸", "8");
        text = text.replaceAll("۹", "9");

        return text;
    }

}
