package com.example.koo_m.stepwithswumans;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Fragment2 extends Fragment {
    NavigationView nv;

    static TextView textView;
    static TextView textViewDB;
    static TextView textViewDB2;
    static TextView textViewDB3;

    public Fragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        nv = (NavigationView) getActivity().findViewById(R.id.nav_view);

        textView = (TextView) view.findViewById(R.id.cntView);
        textViewDB = (TextView) view.findViewById(R.id.todayCntView);
        textViewDB2 = (TextView) view.findViewById(R.id.weekCntView);
        textViewDB3 = (TextView) view.findViewById(R.id.totalCntView);

        if (MainActivity.huehak) {
            Cursor cursor = MainActivity.mDatabase.rawQuery("SELECT DATE FROM WALK WHERE DATE='" + MainActivity.currentDate + "';", null);
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                MainActivity.mDatabase.execSQL("UPDATE WALK SET COUNT='" + 0 + "', HUEHAK='" + MainActivity.huehakCount + "' WHERE DATE='" + MainActivity.currentDate + "';");
            } else
                MainActivity.mDatabase.execSQL("INSERT INTO WALK (DATE,COUNT,HUEHAK) VALUES ('" + MainActivity.currentDate + "'," + 0 + "," + MainActivity.huehakCount + ");");

            Cursor resultSet = MainActivity.mDatabase.rawQuery("SELECT HUEHAK FROM WALK WHERE DATE='" + MainActivity.currentDate + "'", null);
            resultSet.moveToFirst();
            textViewDB.setText(resultSet.getString(0));

            Cursor resultSet2 = MainActivity.mDatabase.rawQuery("SELECT COUNT(HUEHAK) FROM WALK WHERE HUEHAK IS NOT NULL;", null);
            if (resultSet2 != null) {
                resultSet2.moveToFirst();
                int count1 = resultSet2.getInt(0);
                if (count1 < 7) {
                    resultSet2 = MainActivity.mDatabase.rawQuery("SELECT SUM(HUEHAK) FROM WALK;", null);
                } else
                    resultSet2 = MainActivity.mDatabase.rawQuery("SELECT SUM(HUEHAK) FROM WALK WHERE DATE BETWEEN '" + MainActivity.weekAgo + "' AND '" + MainActivity.currentDate + "';", null);
            }
            resultSet2.moveToFirst();
            textViewDB2.setText(resultSet2.getString(0));

            Cursor resultSet3 = MainActivity.mDatabase.rawQuery("SELECT SUM(HUEHAK) FROM WALK;", null);
            resultSet3.moveToFirst();
            if (resultSet3.getInt(0) >= 100) {
                MainActivity.huehak = false;
                Cursor resultSet4 = MainActivity.mDatabase.rawQuery("SELECT DATE FROM WALK WHERE HUEHAk>0", null);
                resultSet4.moveToFirst();
                for (int i = 0; i < resultSet4.getCount(); i++) {
                    MainActivity.mDatabase.execSQL("UPDATE WALK SET COUNT='" + 0 + "',HUEHAK='" + 0 + "' WHERE DATE='" + resultSet4.getString(i) + "';");
                }
            }
            textViewDB3.setText(resultSet3.getString(0));

            textView.setText("" + MainActivity.huehakCount);
        } else {
            Cursor cursor = MainActivity.mDatabase.rawQuery("SELECT DATE FROM WALK WHERE DATE='" + MainActivity.currentDate + "';", null);
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                MainActivity.mDatabase.execSQL("UPDATE WALK SET COUNT='" + MainActivity.count + "' WHERE DATE='" + MainActivity.currentDate + "';");
            } else
                MainActivity.mDatabase.execSQL("INSERT INTO WALK (DATE,COUNT) VALUES ('" + MainActivity.currentDate + "'," + MainActivity.count + ");");

            Cursor resultSet = MainActivity.mDatabase.rawQuery("SELECT COUNT FROM WALK WHERE DATE='" + MainActivity.currentDate + "'", null);
            resultSet.moveToFirst();
            textViewDB.setText(resultSet.getString(0));

            Cursor resultSet2 = MainActivity.mDatabase.rawQuery("SELECT COUNT(*) FROM WALK;", null);
            if (resultSet2 != null) {
                resultSet2.moveToFirst();
                int count1 = resultSet2.getInt(0);
                if (count1 < 7) {
                    resultSet2 = MainActivity.mDatabase.rawQuery("SELECT SUM(COUNT) FROM WALK;", null);
                } else
                    resultSet2 = MainActivity.mDatabase.rawQuery("SELECT SUM(COUNT) FROM WALK WHERE DATE BETWEEN '" + MainActivity.weekAgo + "' AND '" + MainActivity.currentDate + "';", null);
            }
            resultSet2.moveToFirst();
            textViewDB2.setText(resultSet2.getString(0));

            Cursor resultSet3 = MainActivity.mDatabase.rawQuery("SELECT SUM(COUNT) FROM WALK;", null);
            resultSet3.moveToFirst();
            textViewDB3.setText(resultSet3.getString(0));

            textView.setText("" + MainActivity.count);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Counter");
        nv.setCheckedItem(R.id.nav_second_fragment);
        if (MainActivity.huehak) {
            textView.setText("" + MainActivity.huehakCount);
        } else {
            textView.setText("" + MainActivity.count);
        }
    }

}
