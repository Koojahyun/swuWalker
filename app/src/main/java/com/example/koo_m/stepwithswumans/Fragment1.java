package com.example.koo_m.stepwithswumans;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class Fragment1 extends Fragment {
    public Fragment1() {
        // Required empty public constructor
    }
    ImageView map1;
    Button b2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
            b2 = (Button)view.findViewById(R.id.button2);
            map1= (ImageView)view.findViewById(R.id.imageView);
            map1.setVisibility(View.INVISIBLE);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map1.setVisibility(View.VISIBLE);
            }
        });
        // Inflate the layout for this fragment

        return view;
    }


}
