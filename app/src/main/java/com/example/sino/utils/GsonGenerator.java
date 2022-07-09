package com.example.sino.utils;

import static com.example.sino.utils.common.Constant.MAX_ATTACH_FILE_PACKET_SIZE;

import com.example.sino.model.db.ChatMessage;
import com.example.sino.model.db.TmpChatMessage;
import com.example.sino.utils.common.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GsonGenerator {

    public static String mobileNoConfirmationToGson(String mobileNo) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("mobileNo", mobileNo));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters,null);
    }

    public static String sendActivationCodeToGson(String mobileNo, String activationCode) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("mobileNo", mobileNo));
        wsParameters.add(new Util.WSParameter("activationCode", activationCode));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters,null);
    }

    public static String getUserPermissionList(String username, String tokenPass) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("username", username));
        wsParameters.add(new Util.WSParameter("tokenPass", tokenPass));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters,null);
    }

    public static String getUserInfoById(String username, String tokenPass, Long userId) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("username", username));
        wsParameters.add(new Util.WSParameter("tokenPass", tokenPass));
        wsParameters.add(new Util.WSParameter("id", userId));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters,null);
    }

    public static String chatMessageDeliveredReport(String username, String tokenPass, List<Long> messageIdList) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("username", username));
        wsParameters.add(new Util.WSParameter("tokenPass", tokenPass));
        wsParameters.add(new Util.WSParameter("messageIdList", messageIdList));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters,null);
    }

    public static String getCarInfo(String username, String tokenPass, String plateText, String chassis, Boolean chassisImportant) {
        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("username", username));
        wsParameters.add(new Util.WSParameter("tokenPass", tokenPass));
        wsParameters.add(new Util.WSParameter("plateText", plateText));
        wsParameters.add(new Util.WSParameter("chassis", chassis));
        wsParameters.add(new Util.WSParameter("chassisImportant", chassisImportant));
        //json = URLEncoder.encode(json);
        return Util.createJson(wsParameters,null);
    }

    public static String saveChatMessage(String username, String tokenPass, ChatMessage chatMessage) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        TmpChatMessage tmpChatMessage = new TmpChatMessage();


        ArrayList<Util.WSParameter> wsParameters = new ArrayList<>();
        wsParameters.add(new Util.WSParameter("username", username));
        wsParameters.add(new Util.WSParameter("tokenPass", tokenPass));

        tmpChatMessage.setId(chatMessage.id);
        tmpChatMessage.setSenderUserId(chatMessage.getSenderUserId());
        tmpChatMessage.setMessage(chatMessage.getMessage());
        tmpChatMessage.setAttachFileUserFileName(chatMessage.getAttachFileUserFileName());

        if (chatMessage.getAttachFileLocalPath() != null){
            File attachedFile = new File(chatMessage.getAttachFileLocalPath());
            if (attachedFile.exists()) {
                try {
                    FileInputStream inputStream = new FileInputStream(attachedFile);
                    byte[] fileBytes = new byte[MAX_ATTACH_FILE_PACKET_SIZE];

                    int res = inputStream.read(fileBytes);
                    byte[] fixedFileBytes = Arrays.copyOf(fileBytes, res);

                    JSONArray attachmentByteJsonArray = new JSONArray();
                    for (int i = 0; i < fixedFileBytes.length; i++) {
                        byte fileByte = fixedFileBytes[i];
                        attachmentByteJsonArray.put(fileByte);
                    }

                    //chatMessageParameters.add(new Util.WSParameter("attachmentBytes", attachmentByteJsonArray));
                    //chatMessageParameters.add(new Util.WSParameter("attachmentChecksum", ImageUtil.getMD5Checksum(chatMessage.getAttachFileLocalPath())));

                    tmpChatMessage.setAttachmentBytes(attachmentByteJsonArray);
                    tmpChatMessage.setAttachmentChecksum(ImageUtil.getMD5Checksum(chatMessage.getAttachFileLocalPath()));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (chatMessage.getValidUntilDate() != null){
           // chatMessageParameters.add(new Util.WSParameter("validUntilDate", simpleDateFormat.format(chatMessage.getValidUntilDate())));
            tmpChatMessage.setValidUntilDate(simpleDateFormat.format(chatMessage.getValidUntilDate()));
        }

       // chatMessageParameters.add(new Util.WSParameter("chatGroupId", chatMessage.getChatGroupId()));
        //chatMessageParameters.add(new Util.WSParameter("isCreateNewPvChatGroup", chatMessage.getCreateNewPvChatGroup()));

        tmpChatMessage.setChatGroupId(chatMessage.getChatGroupId());
        tmpChatMessage.setCreateNewPvChatGroup(chatMessage.getCreateNewPvChatGroup());

        return Util.createJson(wsParameters,tmpChatMessage);
    }
}
