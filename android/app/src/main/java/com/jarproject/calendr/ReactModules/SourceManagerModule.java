package com.jarproject.calendr.ReactModules;

import android.os.Build;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.gson.Gson;
import com.ziroh.yellowpages.impl.YellowPageManagerImpl;
import com.ziroh.yellowpages.impl.sqlite.common.SourceData;
import com.ziroh.yellowpages.impl.sqlite.source.SourceManagerImpl;
import com.ziroh.yellowpages.ui.source.Source;
import com.ziroh.yellowpages.ui.source.SourceHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class SourceManagerModule extends ReactContextBaseJavaModule {

    private ReactApplicationContext reactContext;

    SourceManagerImpl sourceManager;

    private Gson gson;

    @NonNull
    @Override
    public String getName() {
        return "SourceManagerModule";
    }

    public SourceManagerModule(ReactApplicationContext context) {
        super(context);
        this.reactContext = context;
        this.gson = new Gson();
        String dbPath = getReactApplicationContext().getFilesDir() + File.separator + "local.db";
        this.sourceManager = new SourceManagerImpl(dbPath);
    }

    @ReactMethod
    public void getSources(Promise promise){
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            WritableArray ar = Arguments.createArray();
            WritableMap m = Arguments.createMap();
            m.putString("err", "android os version not supported");
            ar.pushMap(m);
            promise.resolve(ar);
            return;
        }
        try{
            List<SourceData> sourceDataArray = this.sourceManager.getSources().join();
            promise.resolve(this.gson.toJson(sourceDataArray));
        }
        catch(Exception e){
            promise.reject("Exception", e.getMessage());
        }
    }

}
