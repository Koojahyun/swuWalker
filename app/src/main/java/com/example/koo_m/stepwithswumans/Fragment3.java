package com.example.koo_m.stepwithswumans;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment3 extends Fragment {
    NavigationView nv;
    TextView levelVal;
    TextView totalStep;
    ImageView character;

    public Fragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        nv = (NavigationView) getActivity().findViewById(R.id.nav_view);
        levelVal = (TextView) view.findViewById(R.id.levelValue);
        totalStep = (TextView) view.findViewById(R.id.totalWalkCountVal);
        character = (ImageView) view.findViewById(R.id.character);

        Cursor resultSet = MainActivity.mDatabase.rawQuery("SELECT SUM(COUNT) FROM WALK;", null);
        resultSet.moveToFirst();
        int totalSteps = Integer.parseInt(resultSet.getString(0));
        totalStep.setText(totalSteps);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Level");
        nv.setCheckedItem(R.id.nav_third_fragment);
    }
}
