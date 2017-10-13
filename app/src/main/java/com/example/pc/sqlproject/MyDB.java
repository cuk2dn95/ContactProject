package com.example.pc.sqlproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;
import static android.R.attr.y;

/**
 * Created by PC on 10/13/2017.
 */

public class MyDB extends SQLiteOpenHelper {

    final public static  String TABLE_NAME ="mytable";
    final public static  String ID_COLUMN ="_id";
    final public static  String NAME_COLUMN ="name";
    final public static  String PHONE_COLUMN ="phone";
    final public static  String CITY_COLUMN ="city";
    final public static  String GENDER_COLUMN ="gender";

    final private   static  String NAME ="myDB";

    static private int VERSION =1;

    public MyDB(Context context) {
        super(context, NAME, null, VERSION);
    }

    private String create_query = "CREATE TABLE "+TABLE_NAME +" ("+ID_COLUMN
                                   +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                                   +NAME_COLUMN +" TEXT,"+PHONE_COLUMN+" TEXT,"
                                    +CITY_COLUMN+" TEXT,"+GENDER_COLUMN +" TEXT);";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_query);
    }
}
