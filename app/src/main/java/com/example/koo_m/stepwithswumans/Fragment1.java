package com.example.koo_m.stepwithswumans;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class Fragment1 extends Fragment {
    ImageView map1;
    Button b2;
    NavigationView nv;

    public Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        nv = (NavigationView)getActivity().findViewById(R.id.nav_view);
        b2 = (Button) view.findViewById(R.id.button2);
        map1 = (ImageView) view.findViewById(R.id.imageView);
        map1.setVisibility(View.INVISIBLE);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map1.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Map");
        nv.setCheckedItem(R.id.nav_first_fragment);
    }
}
