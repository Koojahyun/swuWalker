package com.example.koo_m.stepwithswumans;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by User on 2016-06-02.
 */
public class DBService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        MainActivity.count = 0;
        super.onCreate();
    }
}
