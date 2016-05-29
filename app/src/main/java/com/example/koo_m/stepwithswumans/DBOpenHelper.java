package com.example.koo_m.stepwithswumans;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by koo_m on 2016-05-29.
 */
public class DBOpenHelper {
    private static final String DATABASE_NAME = "counter.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DBHelper mDBHelper;
    private Context mCtx;

    public class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Database.CreateDB.CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+Database.CreateDB.TABLENAME);
            onCreate(db);
        }
    }

    public DBOpenHelper(Context context) {
        mCtx = context;
    }

    public DBOpenHelper open() throws SQLException {
        mDBHelper = new DBHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDB.close();
    }

}
