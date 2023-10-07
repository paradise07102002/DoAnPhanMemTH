package edu.huflit.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabase {
    SQLiteDatabase database;
    DBHelper helper;

    public MyDatabase(Context context)
    {
        helper = new DBHelper(context);
        database = helper.getWritableDatabase();
    }
}
