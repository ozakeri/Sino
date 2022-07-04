package com.example.sino.utils;

import com.example.sino.utils.common.Util;

import java.util.ArrayList;
import java.util.List;

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

    public static String getUserPermissionList(String username,String tokenPass) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("username", username));
        wsParameters.add(new Util.WSParameter("tokenPass", tokenPass));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters);
    }

    public static String chatMessageDeliveredReport(String username, String tokenPass, List<Long> messageIdList) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("username", username));
        wsParameters.add(new Util.WSParameter("tokenPass", tokenPass));
        wsParameters.add(new Util.WSParameter("messageIdList", messageIdList));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters);
    }

    public static String getCarInfo(String username, String tokenPass, String plateText,String chassis,Boolean chassisImportant) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("username", username));
        wsParameters.add(new Util.WSParameter("tokenPass", tokenPass));
        wsParameters.add(new Util.WSParameter("plateText", plateText));
        wsParameters.add(new Util.WSParameter("chassis", chassis));
        wsParameters.add(new Util.WSParameter("chassisImportant", chassisImportant));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters);
    }
}
