package com.jarproject.calendr.ReactModules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.gson.Gson;
import com.ziroh.yellowpages.impl.sqlite.common.ContactData;
import com.ziroh.yellowpages.impl.sqlite.common.GroupData;
import com.ziroh.yellowpages.impl.sqlite.util.RandomData;

public class RandomDataGenerator extends ReactContextBaseJavaModule {

    Gson gson;
    ReactApplicationContext reactApplicationContext;
    RandomData randomData;
    public RandomDataGenerator(ReactApplicationContext context){
        super(context);
        this.reactApplicationContext = context;
        this.gson = new Gson();
        this.randomData = new RandomData();
    }

    @NonNull
    @Override
    public String getName() {
        return "RandomDataGenerator";
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    String contact(){
        ContactData contaData = this.randomData.contact();
        return this.gson.toJson(contaData);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    String group(){
        GroupData groupData = this.randomData.group();
        return this.gson.toJson(groupData);
    }
}
