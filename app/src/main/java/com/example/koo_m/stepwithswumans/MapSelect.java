package com.example.koo_m.stepwithswumans;

public class MapSelect{
    int[][] loc = {{R.drawable.swsm_mapclass01,R.drawable.swsm_mapclass02,R.drawable.swsm_mapclass03,R.drawable.swsm_mapclass04,R.drawable.swsm_mapclass05,R.drawable.swsm_mapclass06,R.drawable.swsm_mapclass07,R.drawable.swsm_mapclass08,R.drawable.swsm_mapclass09,R.drawable.swsm_mapclass10,R.drawable.swsm_mapclass11},{R.drawable.swsm_mapclass1_1,R.drawable.swsm_mapclass1_2,R.drawable.swsm_mapclass1_3,R.drawable.swsm_mapclass1_4,R.drawable.swsm_mapclass1_5,R.drawable.swsm_mapclass1_6,R.drawable.swsm_mapclass1_7,R.drawable.swsm_mapclass1_8,R.drawable.swsm_mapclass1_9,R.drawable.swsm_mapclass1_10,R.drawable.swsm_mapclass1_11}};
    MapSelect(){}

    public int getImageId(int x, int y){
        return loc[x][y];
    }
}
