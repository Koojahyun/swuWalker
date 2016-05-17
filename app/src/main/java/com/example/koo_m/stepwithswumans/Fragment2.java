package com.example.koo_m.stepwithswumans;

import android.content.Context;
import android.content.Intent;
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
    int count;

    public Fragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        nv = (NavigationView) getActivity().findViewById(R.id.nav_view);

        textView = (TextView) view.findViewById(R.id.cntView);

        textView.setText("" + MainActivity.count);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Counter");
        nv.setCheckedItem(R.id.nav_second_fragment);
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
