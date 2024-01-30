package com.jarproject.calendr.ReactModules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.ziroh.yellowpages.impl.sqlite.util.SqliteInitializer;

import java.io.File;

public class CommonModule extends ReactContextBaseJavaModule {

    @NonNull
    @Override
    public String getName() {
        return "CommonModule";
    }

    ReactApplicationContext reactApplicationContext;

    public CommonModule(ReactApplicationContext context){
        super(context);
        this.reactApplicationContext = context;
    }

    @ReactMethod
    public void init(Promise promise){
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            WritableArray ar = Arguments.createArray();
            WritableMap m = Arguments.createMap();
            m.putString("err", "android os version not supported");
            ar.pushMap(m);
            promise.resolve(ar);
            return;
        }

        String dbPath = getReactApplicationContext().getFilesDir() + File.separator + "local.db";
        try{
            Class.forName("org.sqlite.JDBC");
            new SqliteInitializer(dbPath);
            promise.resolve("Successs");
        }
        catch (Exception e){
            promise.reject("Exception",e.getMessage());
        }
    }

}
