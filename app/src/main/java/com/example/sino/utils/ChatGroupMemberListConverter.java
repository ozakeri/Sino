package com.example.sino.utils;

import androidx.room.TypeConverter;

import com.example.sino.model.db.ChatGroupMember;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ChatGroupMemberListConverter {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<ChatGroupMember> stringToChatGroupMemberList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ChatGroupMember>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<ChatGroupMember> someObjects) {
        return gson.toJson(someObjects);
    }
}
