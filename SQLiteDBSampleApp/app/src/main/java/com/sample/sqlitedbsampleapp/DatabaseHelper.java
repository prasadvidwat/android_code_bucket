package com.sample.sqlitedbsampleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "contact.db";
    final String TABLE_NAME = "contact_table";
    final String CONTACT_ID = "id";
    final String CONTACT_NAME = "name";
    final String CONTACT_NUMBER = "number";

    final static int DATABASE_VERSION = 1;

    final String create_table_query = "Create table "+TABLE_NAME+" ("+CONTACT_ID+" integer primary key autoincrement,"+
        CONTACT_NAME+" varchar(30),"+CONTACT_NUMBER+" varchar(10))";

    final String delete_table_query = "Drop table if exists "+TABLE_NAME;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(delete_table_query);
        onCreate(sqLiteDatabase);
    }

}
