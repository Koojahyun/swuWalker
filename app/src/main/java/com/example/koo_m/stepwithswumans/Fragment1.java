package com.example.koo_m.stepwithswumans;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;


public class Fragment1 extends Fragment {
    ImageView map1;
    Button b2;
    Button b3;
    Button b4;
    NavigationView nv;

    String[] building = {"정문","50주년 기념관","기독교 교육관","인문 사회관","대강당","조형 예술관","학생 누리관","중앙 도서관","체육관","바롬인성 교육관","제1과학관","제2과학관","남문"};

    public Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);

        final ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,building);
        final Spinner sp = new Spinner(getActivity());
        sp.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        sp.setAdapter(adp);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(sp);
                builder.create().show();
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
