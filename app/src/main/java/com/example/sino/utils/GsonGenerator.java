package com.example.sino.utils;

import android.content.Context;

import java.net.URLEncoder;
import java.util.ArrayList;

public class GsonGenerator {

    public static String mobileNoConfirmationToGson(String mobileNo) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("mobileNo", mobileNo));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters);
    }

    public static String sendActivationCodeToGson(String mobileNo,String activationCode) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("mobileNo", mobileNo));
        wsParameters.add(new Util.WSParameter("activationCode", activationCode));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters);
    }
}
