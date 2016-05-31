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


public class Fragment2 extends Fragment{
    NavigationView nv;

    static TextView textView;
    static TextView textViewDB;
    static TextView textViewDB2;

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

        textView.setText("" + MainActivity.count);

        Cursor resultSet = MainActivity.mDatabase.rawQuery("SELECT * FROM COUNT",null);
        resultSet.moveToFirst();
        textViewDB.setText(resultSet.getString(2));
        Cursor resultSet2 = MainActivity.mDatabase.rawQuery("SELECT SUM(COUNT) FROM COUNT WHERE DATE BETWEEN 2016-05-28 AND 2016-06-03",null);
        resultSet2.moveToFirst();
        textViewDB2.setText(resultSet2.getString(2));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Counter");
        nv.setCheckedItem(R.id.nav_second_fragment);
        textView.setText("" + MainActivity.count);
    }




    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
