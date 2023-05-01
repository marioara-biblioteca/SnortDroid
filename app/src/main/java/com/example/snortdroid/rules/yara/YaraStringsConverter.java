package com.example.snortdroid.rules.yara;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class YaraStringsConverter {
   static Gson gson = new Gson();

    @TypeConverter
    public static List<YaraStrings> stringToSomeObjectList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<YaraStrings>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<YaraStrings> someObjects) {
        return gson.toJson(someObjects);
    }
}
