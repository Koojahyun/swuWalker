
package com.example.koo_m.stepwithswumans;

public class MapSelect {
    static int[][] loc = {{0, R.drawable.swsm_mapclass01, R.drawable.swsm_mapclass02, R.drawable.swsm_mapclass03, R.drawable.swsm_mapclass04, R.drawable.swsm_mapclass05,
            R.drawable.swsm_mapclass06, R.drawable.swsm_mapclass07, R.drawable.swsm_mapclass08, R.drawable.swsm_mapclass09, R.drawable.swsm_mapclass10,
            R.drawable.swsm_mapclass11, R.drawable.swsm_mapclass12}, {0, 0, R.drawable.swsm_mapclass13, R.drawable.swsm_mapclass14, R.drawable.swsm_mapclass15,
            R.drawable.swsm_mapclass16, R.drawable.swsm_mapclass17, R.drawable.swsm_mapclass18, R.drawable.swsm_mapclass19, R.drawable.swsm_mapclass20,
            R.drawable.swsm_mapclass21, R.drawable.swsm_mapclass22, R.drawable.swsm_mapclass23}, {0, 0, 0, R.drawable.swsm_mapclass24, R.drawable.swsm_mapclass25, R.drawable.swsm_mapclass26,
            R.drawable.swsm_mapclass27, R.drawable.swsm_mapclass28, R.drawable.swsm_mapclass29, R.drawable.swsm_mapclass30, R.drawable.swsm_mapclass31, R.drawable.swsm_mapclass32,
            R.drawable.swsm_mapclass33}, {0, 0, 0, 0, R.drawable.swsm_mapclass34, R.drawable.swsm_mapclass35, R.drawable.swsm_mapclass36, R.drawable.swsm_mapclass37, R.drawable.swsm_mapclass38,
            R.drawable.swsm_mapclass39, R.drawable.swsm_mapclass40, R.drawable.swsm_mapclass41, R.drawable.swsm_mapclass42}, {0, 0, 0, 0, 0, R.drawable.swsm_mapclass43, R.drawable.swsm_mapclass44,
            R.drawable.swsm_mapclass45, R.drawable.swsm_mapclass46, R.drawable.swsm_mapclass47, R.drawable.swsm_mapclass48, R.drawable.swsm_mapclass49, R.drawable.swsm_mapclass50}, {0, 0, 0, 0, 0, 0,
            R.drawable.swsm_mapclass51, R.drawable.swsm_mapclass52, R.drawable.swsm_mapclass53, R.drawable.swsm_mapclass54, R.drawable.swsm_mapclass55, R.drawable.swsm_mapclass56,
            R.drawable.swsm_mapclass57}, {0, 0, 0, 0, 0, 0, 0, R.drawable.swsm_mapclass58, R.drawable.swsm_mapclass59, R.drawable.swsm_mapclass60, R.drawable.swsm_mapclass61, R.drawable.swsm_mapclass62,
            R.drawable.swsm_mapclass63}, {0, 0, 0, 0, 0, 0, 0, 0, R.drawable.swsm_mapclass64, R.drawable.swsm_mapclass65, R.drawable.swsm_mapclass66, R.drawable.swsm_mapclass67, R.drawable.swsm_mapclass68}, {
            0, 0, 0, 0, 0, 0, 0, 0, 0, R.drawable.swsm_mapclass69, R.drawable.swsm_mapclass70, R.drawable.swsm_mapclass71, R.drawable.swsm_mapclass72}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, R.drawable.swsm_mapclass73, R.drawable.swsm_mapclass74,
            R.drawable.swsm_mapclass75}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, R.drawable.swsm_mapclass76, R.drawable.swsm_mapclass77}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, R.drawable.swsm_mapclass78}};

    static int[] walk = {R.drawable.swsm_mapwalk01, R.drawable.swsm_mapwalk02, R.drawable.swsm_mapwalk03, R.drawable.swsm_mapwalk04, R.drawable.swsm_mapwalk05, R.drawable.swsm_mapwalk06};
    static int[] diet = {R.drawable.swsm_mapdiet01, R.drawable.swsm_mapdiet02, R.drawable.swsm_mapdiet03, R.drawable.swsm_mapdiet04, R.drawable.swsm_mapdiet05};

    public static int getImageId(int x, int y) {
        return loc[x][y];
    }

    public static int getWalkId(int x) {
        return walk[x];
    }

    public static int getDiet(int x) {
        return diet[x];
    }

    MapSelect() {

    }

}

