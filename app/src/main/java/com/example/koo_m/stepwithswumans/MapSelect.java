
package com.example.koo_m.stepwithswumans;

public class MapSelect {
    int[][] loc = {{R.drawable.swsm_mapclass01, R.drawable.swsm_mapclass02, R.drawable.swsm_mapclass03, R.drawable.swsm_mapclass04, R.drawable.swsm_mapclass05,
            R.drawable.swsm_mapclass06, R.drawable.swsm_mapclass07, R.drawable.swsm_mapclass08, R.drawable.swsm_mapclass09, R.drawable.swsm_mapclass10,
            R.drawable.swsm_mapclass11}, {R.drawable.swsm_mapclass12, R.drawable.swsm_mapclass13, R.drawable.swsm_mapclass14,
            R.drawable.swsm_mapclass15, R.drawable.swsm_mapclass16, R.drawable.swsm_mapclass17, R.drawable.swsm_mapclass18, R.drawable.swsm_mapclass19,
            R.drawable.swsm_mapclass20, R.drawable.swsm_mapclass21, R.drawable.swsm_mapclass22}};

    public int getImageId(int x, int y) {
        return loc[x][y];
    }

    MapSelect() {

    }

}

