package com.example.koo_m.stepwithswumans;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int screenHeight = outMetrics.heightPixels;
        int imageHeight = screenHeight / 2;
        int imageWidth = imageHeight / 16 * 9;

        RelativeLayout.LayoutParams imageParam = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
        imageParam.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        character.setLayoutParams(imageParam);

        if (MainActivity.huehak) {
            try {
                Cursor resultSet = MainActivity.mDatabase.rawQuery("SELECT SUM(HUEHAK) FROM WALK;", null);
                resultSet.moveToFirst();
                int huehakSteps = Integer.parseInt(resultSet.getString(0));
                character.setImageResource(R.drawable.swsm_cha09);
                character.setVisibility(View.VISIBLE);
                levelVal.setText("지금은 휴학상태 입니다.");
                totalStep.setText("복학까지 앞으로 남은 걸음은 " + (100 - huehakSteps) + " 입니다.");
            }catch (Exception e){
                e.printStackTrace();
                e.getMessage();
            }
        } else {

            try {
                Cursor resultSet = MainActivity.mDatabase.rawQuery("SELECT SUM(COUNT) FROM WALK;", null);
                resultSet.moveToFirst();
                int totalSteps = Integer.parseInt(resultSet.getString(0));
                if (totalSteps < 70000) {
                    character.setImageResource(R.drawable.swsm_cha01);
                    character.setVisibility(View.VISIBLE);
                    levelVal.setText("지금은 1학년 1학기 입니다.");
                    totalStep.setText("다음 학기까지 앞으로 남은 걸음은 " + (70000 - totalSteps) + " 입니다.");

                } else if (totalSteps >= 70000 && totalSteps < 210000) {
                    character.setImageResource(R.drawable.swsm_cha02);
                    character.setVisibility(View.VISIBLE);
                    levelVal.setText("지금은 1학년 2학기 입니다.");
                    totalStep.setText("다음 학기까지 앞으로 남은 걸음은 " + (210000 - totalSteps) + " 입니다.");

                } else if (totalSteps >= 210000 && totalSteps < 420000) {
                    character.setImageResource(R.drawable.swsm_cha03);
                    character.setVisibility(View.VISIBLE);
                    levelVal.setText("지금은 2학년 1학기 입니다.");
                    totalStep.setText("다음 학기까지 앞으로 남은 걸음은 " + (420000 - totalSteps) + " 입니다.");

                } else if (totalSteps >= 420000 && totalSteps < 700000) {
                    character.setImageResource(R.drawable.swsm_cha04);
                    character.setVisibility(View.VISIBLE);
                    levelVal.setText("지금은 2학년 2학기 입니다.");
                    totalStep.setText("다음 학기까지 앞으로 남은 걸음은 " + (700000 - totalSteps) + " 입니다.");

                } else if (totalSteps >= 700000 && totalSteps < 1050000) {
                    character.setImageResource(R.drawable.swsm_cha05);
                    character.setVisibility(View.VISIBLE);
                    levelVal.setText("지금은 3학년 1학기 입니다.");
                    totalStep.setText("다음 학기까지 앞으로 남은 걸음은 " + (1050000 - totalSteps) + " 입니다.");

                } else if (totalSteps >= 1050000 && totalSteps < 1470000) {
                    character.setImageResource(R.drawable.swsm_cha06);
                    character.setVisibility(View.VISIBLE);
                    levelVal.setText("지금은 3학년 2학기 입니다.");
                    totalStep.setText("다음 학기까지 앞으로 남은 걸음은 " + (1470000 - totalSteps) + " 입니다.");

                } else if (totalSteps >= 1470000 && totalSteps < 1960000) {
                    character.setImageResource(R.drawable.swsm_cha07);
                    character.setVisibility(View.VISIBLE);
                    levelVal.setText("지금은 4학년 1학기 입니다.");
                    totalStep.setText("다음 학기까지 앞으로 남은 걸음은 " + (1960000 - totalSteps) + " 입니다.");

                } else if (totalSteps >= 1960000) {
                    character.setImageResource(R.drawable.swsm_cha08);
                    character.setVisibility(View.VISIBLE);
                    levelVal.setText("지금은 마지막 학기 입니다.");
                }
            } catch (Exception e) {
                e.getStackTrace();
                e.getMessage();
            }
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Level");
        nv.setCheckedItem(R.id.nav_third_fragment);
    }
}
