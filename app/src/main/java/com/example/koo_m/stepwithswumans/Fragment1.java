package com.example.koo_m.stepwithswumans;


import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;


public class Fragment1 extends Fragment {
    ImageView map1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;
    NavigationView nv;
    String[] building = {"정문", "남문", "체육관", "50주년 기념관", "기독교 교육관", "인문사회관", "대강당", "제1과학관", "제2과학관", "누리관", "중앙도서관", "바롬인성교육관", "예능관"};

    public Fragment1() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_main, container, false);

        nv = (NavigationView) getActivity().findViewById(R.id.nav_view);
        b2 = (ImageButton) view.findViewById(R.id.button2);
        b3 = (ImageButton) view.findViewById(R.id.button3);
        b4 = (ImageButton) view.findViewById(R.id.button4);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), b2);
                popup.getMenuInflater().inflate(R.menu.diet_popup, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.diet_10:
                                view.setBackgroundResource(MapSelect.getDiet(0));
                                break;
                            case R.id.diet_15:
                                view.setBackgroundResource(MapSelect.getDiet(1));
                                break;
                            case R.id.diet_20:
                                view.setBackgroundResource(MapSelect.getDiet(2));
                                break;
                            case R.id.diet_25:
                                view.setBackgroundResource(MapSelect.getDiet(3));
                                break;
                            case R.id.diet_30:
                                view.setBackgroundResource(MapSelect.getDiet(4));
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), b3);
                popup.getMenuInflater().inflate(R.menu.walk_popup, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.walk1:
                                view.setBackgroundResource(MapSelect.getWalkId(0));
                                break;
                            case R.id.walk2:
                                view.setBackgroundResource(MapSelect.getWalkId(1));
                                break;
                            case R.id.walk3:
                                view.setBackgroundResource(MapSelect.getWalkId(2));
                                break;
                            case R.id.walk4:
                                view.setBackgroundResource(MapSelect.getWalkId(3));
                                break;
                            case R.id.walk5:
                                view.setBackgroundResource(MapSelect.getWalkId(4));
                                break;
                            case R.id.walk6:
                                view.setBackgroundResource(MapSelect.getWalkId(5));
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View alertLayout = null;

                if (alertLayout == null) {
                    alertLayout = inflater.inflate(R.layout.layout_dialog, null, false);
                } else {
                    ((ViewGroup) alertLayout.getParent()).removeView(alertLayout);
                }
                final Spinner startLocation = (Spinner) alertLayout.findViewById(R.id.startLocation);
                final Spinner destLocation = (Spinner) alertLayout.findViewById(R.id.destLocation);

                ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, building);

                startLocation.setPrompt("강의실을 선택해주세요");
                destLocation.setPrompt("강의실을 선택해주세요");
                startLocation.setAdapter(adapter);
                destLocation.setAdapter(adapter);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("강의실 이동");
                alert.setView(alertLayout);
                alert.setCancelable(true);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //지도 뜨도록 구현
                        try {
                            int x = startLocation.getSelectedItemPosition();
                            int y = destLocation.getSelectedItemPosition();
                            if (x == y) {
                                view.setBackgroundResource(R.drawable.mapdefault1);
                                AlertDialog.Builder popup = new AlertDialog.Builder(getActivity());
                                popup.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                popup.setMessage("같은 곳을 선택했어요!");
                                popup.show();

                            } else {
                                if (x > y) {
                                    int a = x;
                                    int b = y;
                                    x = b;
                                    y = a;
                                }
                                view.setBackgroundResource(MapSelect.getImageId(x, y));
                            }
                        } catch (Exception e) {
                            e.getMessage();
                            e.getStackTrace();
                        }
                    }
                });
                alert.show();
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




