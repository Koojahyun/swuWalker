package com.example.koo_m.stepwithswumans;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        Toast.makeText(getApplicationContext(), "DBService Activate", Toast.LENGTH_SHORT).show();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calDayAgo = Calendar.getInstance();
        calDayAgo.add(Calendar.DATE, -1);
        String currentDate = dateFormat.format(new Date());
        String dayAgo = dateFormat.format(calDayAgo.getTime());

        Calendar calWeekAgo = Calendar.getInstance();
        calWeekAgo.add(Calendar.DATE, -6);
        String weekAgo = dateFormat.format(calWeekAgo.getTime());


        if (BackgroundService.backcount > 0)
            MainActivity.mDatabase.execSQL("UPDATE WALK SET COUNT='" + BackgroundService.backcount + "' WHERE DATE='" + dayAgo + "';");
        else
            MainActivity.mDatabase.execSQL("UPDATE WALK SET COUNT='" + MainActivity.count + "' WHERE DATE='" + dayAgo + "';");

        MainActivity.count = 0;
        BackgroundService.backcount = 0;

//        Fragment2.textView.setText(MainActivity.count);
        MainActivity.currentDate = currentDate;
        MainActivity.weekAgo = weekAgo;

        MainActivity.mDatabase.execSQL("INSERT INTO WALK (DATE,COUNT) VALUES ('" + currentDate + "'," + MainActivity.count + ");");
        super.onCreate();
    }
}
