package com.example.koo_m.stepwithswumans;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;


public class Fragment1 extends Fragment {
    ImageView map1;
    Button b2;
    Button b3;
    Button b4;
    NavigationView nv;

    public Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        ImageView iv[][] = new ImageView[13][13];

        nv = (NavigationView)getActivity().findViewById(R.id.nav_view);
        b2 = (Button) view.findViewById(R.id.button2);
        b3 = (Button)view.findViewById(R.id.button3);
        b4 = (Button)view.findViewById(R.id.button4);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"다이어트를 선택하셨습니다",Toast.LENGTH_SHORT).show();
                PopupMenu popup = new PopupMenu(getActivity(),b2);
                popup.getMenuInflater().inflate(R.menu.diet_popup,popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getActivity(),item.getTitle() + " 코스를 선택하셨습니다",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"산책을 선택하셨습니다",Toast.LENGTH_SHORT).show();
            }
        });

        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"강의실이동을 선택하셨습니다",Toast.LENGTH_SHORT).show();

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
