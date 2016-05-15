package com.example.koo_m.stepwithswumans;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Fragment2 extends Fragment {
    NavigationView nv;

    public Fragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        nv = (NavigationView) getActivity().findViewById(R.id.nav_view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Counter");
        nv.setCheckedItem(R.id.nav_first_fragment);
        Toast.makeText(getActivity(), "Counter", Toast.LENGTH_SHORT).show();
    }
}
