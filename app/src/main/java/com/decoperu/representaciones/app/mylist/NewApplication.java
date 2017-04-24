package com.decoperu.representaciones.app.mylist;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.decoperu.representaciones.app.mylist.api.NewsApi;

/**
 * Created by aoki on 24/04/2017.
 */

public class NewApplication extends Application {

    private static NewApplication instance;

    NewsApi newsApi;

    public NewApplication(){
        super();
        instance = this;
    }

    public static NewApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        newsApi = new NewsApi();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
