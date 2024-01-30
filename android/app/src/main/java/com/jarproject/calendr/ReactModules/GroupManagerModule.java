package com.jarproject.calendr.ReactModules;

import android.os.Build;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.gson.Gson;
import com.ziroh.yellowpages.impl.YellowPageManagerImpl;
import com.ziroh.yellowpages.impl.sqlite.common.ContactData;
import com.ziroh.yellowpages.impl.sqlite.common.GroupData;
import com.ziroh.yellowpages.impl.sqlite.group.GroupManagerImpl;
import com.ziroh.yellowpages.ui.contact.ContactHandler;
import com.ziroh.yellowpages.ui.contact.view.ContactView;
import com.ziroh.yellowpages.ui.group.Group;
import com.ziroh.yellowpages.ui.group.GroupEditor;
import com.ziroh.yellowpages.ui.group.GroupHandler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class GroupManagerModule extends ReactContextBaseJavaModule {
    private ReactApplicationContext reactContext;
    private Gson gson;

    GroupManagerImpl groupManager;

    @NonNull
    @Override
    public String getName() {
        return "GroupManagerModule";
    }

    public GroupManagerModule(ReactApplicationContext context) {
        super(context);
        this.reactContext = context;
        this.gson = new Gson();
        String dbPath = getReactApplicationContext().getFilesDir() + File.separator + "local.db";
        this.groupManager = new GroupManagerImpl(dbPath);
    }

    @ReactMethod
    public void create(ReadableMap groupDataMap, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            String groupDataJsonString = groupDataMap.toString();
            this.groupManager.create(this.gson.fromJson(groupDataJsonString, GroupData.class)).join();
            promise.resolve(this.gson.toJson("Success"));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void edit(ReadableMap groupDataMap, Promise promise) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            WritableArray ar = Arguments.createArray();
            WritableMap m = Arguments.createMap();
            m.putString("err", "android os version not supported");
            ar.pushMap(m);
            promise.resolve(ar);
            return;
        }
        try {
            String groupDataJsonString = groupDataMap.toString();
            this.groupManager.edit(this.gson.fromJson(groupDataJsonString, GroupData.class)).join();
            promise.resolve(this.gson.toJson("Success"));
        } catch (Exception e) {
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void getAll(int limit, int offset, Promise promise) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            WritableArray ar = Arguments.createArray();
            WritableMap m = Arguments.createMap();
            m.putString("err", "android os version not supported");
            ar.pushMap(m);
            promise.resolve(ar);
            return;
        }
        try {
            List<GroupData>  groupDataArray =  this.groupManager.getAll(limit, offset).join();
            promise.resolve(this.gson.toJson(groupDataArray));
        } catch (Exception e) {
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void get(String id, Promise promise) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            WritableArray ar = Arguments.createArray();
            WritableMap m = Arguments.createMap();
            m.putString("err", "android os version not supported");
            ar.pushMap(m);
            promise.resolve(ar);
            return;
        }
        try {
            GroupData groupData = this.groupManager.get(id).join();
            promise.resolve(this.gson.toJson(groupData));
        } catch (Exception e) {
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void delete(String id, Promise promise) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            WritableArray ar = Arguments.createArray();
            WritableMap m = Arguments.createMap();
            m.putString("err", "android os version not supported");
            ar.pushMap(m);
            promise.resolve(ar);
            return;
        }
        try {
            this.groupManager.delete(id).join();
            promise.resolve(this.gson.toJson("Success"));
        } catch (Exception e) {
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void getCount(Promise promise) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            WritableArray ar = Arguments.createArray();
            WritableMap m = Arguments.createMap();
            m.putString("err", "android os version not supported");
            ar.pushMap(m);
            promise.resolve(ar);
            return;
        }
        try {
            int count = this.groupManager.getCount().join();
            promise.resolve(count);
        } catch (Exception e) {
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void searchGroup(String keyword, int limit, int offset, Promise promise) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            WritableArray ar = Arguments.createArray();
            WritableMap m = Arguments.createMap();
            m.putString("err", "android os version not supported");
            ar.pushMap(m);
            promise.resolve(ar);
            return;
        }
        try {
            List<GroupData> groupDataArray = this.groupManager.searchGroup(keyword, limit, offset).join();
            promise.resolve(this.gson.toJson(groupDataArray));
        } catch (Exception e) {
            promise.reject("Exception", e.getMessage());
        }
    }


}
