package com.jarproject.calendr;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.jarproject.calendr.ReactModules.CommonModule;
import com.jarproject.calendr.ReactModules.ContactManagerModule;
import com.jarproject.calendr.ReactModules.GroupManagerModule;
import com.jarproject.calendr.ReactModules.RandomDataGenerator;
import com.jarproject.calendr.ReactModules.SourceManagerModule;

import java.util.ArrayList;
import java.util.List;

public class YellowPagesPackage implements ReactPackage {


    static {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Called this");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new ContactManagerModule(reactContext));
        modules.add(new GroupManagerModule(reactContext));
        modules.add(new SourceManagerModule(reactContext));
        modules.add(new RandomDataGenerator(reactContext));
        modules.add(new CommonModule(reactContext));
        return modules;
    }

    @NonNull
    @Override
    public ArrayList<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
        return new ArrayList<com.facebook.react.uimanager.ViewManager>();
    }
}
