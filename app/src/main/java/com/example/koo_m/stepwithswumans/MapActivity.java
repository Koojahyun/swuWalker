package com.example.koo_m.stepwithswumans;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MapActivity extends AppCompatActivity{
    ImageView map1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        Button b2 = (Button)findViewById(R.id.button2);
        map1= (ImageView)findViewById(R.id.imageView);
        map1.setVisibility(View.INVISIBLE);

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                map1.setVisibility(View.VISIBLE);
                    }
        });
    }

}
