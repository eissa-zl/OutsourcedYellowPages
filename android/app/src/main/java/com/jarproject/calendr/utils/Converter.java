package com.jarproject.calendr.utils;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.ziroh.yellowpages.impl.sqlite.common.GroupData;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static  List<String> convertReadableArrayToList(ReadableArray readableArray) {
        List<String> stringList = new ArrayList<>();

        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                // Assuming the array contains strings
                String stringValue = readableArray.getString(i);
                stringList.add(stringValue);
            }
        }
        return stringList;
    }
}
