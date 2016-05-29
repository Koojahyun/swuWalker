package com.example.koo_m.stepwithswumans;

import android.provider.BaseColumns;

/**
 * Created by koo_m on 2016-05-28.
 */
public final class Database {
    public static final class CreateDB implements BaseColumns {
//        public static final String YEAR = "year";
//        public static final String MONTH = "month";
//        public static final String DAY = "day";
        public static final String DATE = "date";
        public static final String COUNT = "count";
        public static final String TABLENAME = "records";
        public static final String CREATE = "create table" + TABLENAME + "(" + _ID + " integer primary key autoincrement, " + DATE + " date not null, " + COUNT + " integer not null );";
    }
}
