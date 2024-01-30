package com.jarproject.calendr.ReactModules;

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
import com.jarproject.calendr.utils.Converter;
import com.ziroh.yellowpages.impl.YellowPageManagerImpl;
import com.ziroh.yellowpages.impl.sqlite.common.ContactData;
import com.ziroh.yellowpages.impl.sqlite.contact.ContactManagerImpl;
import com.ziroh.yellowpages.ui.contact.ContactEditor;
import com.ziroh.yellowpages.ui.contact.ContactHandler;
import com.ziroh.yellowpages.ui.contact.ContactManager;
import com.ziroh.yellowpages.ui.contact.data.Address;
import com.ziroh.yellowpages.ui.contact.view.ContactView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ContactManagerModule extends ReactContextBaseJavaModule {

    private ReactApplicationContext reactContext;
    private Gson gson;

    private ContactManagerImpl contactManager;

    @NonNull
    @Override
    public String getName() {
        return "ContactManagerModule";
    }

    public ContactManagerModule(ReactApplicationContext context) {
        super(context);
        this.reactContext = context;
        this.gson = new Gson();
        String dbPath = getReactApplicationContext().getFilesDir() + File.separator + "local.db";
        this.contactManager = new ContactManagerImpl(dbPath);
    }

    @ReactMethod
    public void create(ReadableMap contactMap, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            String contactDataJsonString = contactMap.toString();
            String response = this.contactManager.create(this.gson.fromJson(contactDataJsonString, ContactData.class)).join();
            promise.resolve(this.gson.toJson(response));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void get(String id, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            ContactData response = this.contactManager.get(id).join();
            promise.resolve(this.gson.toJson(response));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void getAll(int limit, int offset, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            List<ContactData> response = this.contactManager.getAll(limit, offset).join();
            promise.resolve(this.gson.toJson(response));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void getByGroup(String groupId, int limit, int offset, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            List<ContactData> response = this.contactManager.getByGroup( groupId,  limit,  offset).join();
            promise.resolve(this.gson.toJson(response));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void getFavourites( int limit, int offset, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            List<ContactData> response = this.contactManager.getFavourites(   limit,  offset).join();
            promise.resolve(this.gson.toJson(response));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void search(String keyword, int limit, int offset, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            List<ContactData> response = this.contactManager.search( keyword,  limit,  offset).join();
            promise.resolve(this.gson.toJson(response));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void getBySource(ReadableArray sourceIdsReadableArray, int limit, int offset, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }

            List<String> sourceIds = Converter.convertReadableArrayToList(sourceIdsReadableArray);
            List<ContactData> response = this.contactManager.getBySource(sourceIds,  limit,  offset).join();
            promise.resolve(this.gson.toJson(response));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void delete(String id, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            this.contactManager.delete(id).join();
            promise.resolve(this.gson.toJson("Success"));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void edit(ReadableMap contactMap, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            String contactDataJsonString = contactMap.toString();
            this.contactManager.edit(this.gson.fromJson(contactDataJsonString, ContactData.class)).join();
            promise.resolve(this.gson.toJson("Success"));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void editFavourites(boolean value, String id, Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            this.contactManager.editFavourites(value, id).join();
            promise.resolve(this.gson.toJson("Success"));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void getCount( Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            int count = this.contactManager.getCount().join();
            promise.resolve(count);
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

    @ReactMethod
    public void getFavouriteCount( Promise promise){
        try{
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                WritableArray ar = Arguments.createArray();
                WritableMap m = Arguments.createMap();
                m.putString("err", "android os version not supported");
                ar.pushMap(m);
                promise.resolve(ar);
                return;
            }
            int count = this.contactManager.getFavouriteCount().join();
            promise.resolve(count);
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

}