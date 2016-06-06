package com.example.koo_m.stepwithswumans;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

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
        Toast.makeText(getApplicationContext(),"DBService Activate",Toast.LENGTH_SHORT).show();
        if(BackgroundService.backcount >0 )
            MainActivity.mDatabase.execSQL("UPDATE WALK SET COUNT='"+BackgroundService.backcount+"' WHERE DATE='"+MainActivity.currentDate+"';");
        else
             MainActivity.mDatabase.execSQL("UPDATE WALK SET COUNT='"+MainActivity.count+"' WHERE DATE='"+MainActivity.currentDate+"';");
        MainActivity.count = 0;
        BackgroundService.backcount = 0;
        super.onCreate();
    }
}
