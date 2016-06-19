package com.home.searching;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.deleteRealm(realmConfig); // Delete Realm between app restarts.
        Realm.setDefaultConfiguration(realmConfig);
    }
}
