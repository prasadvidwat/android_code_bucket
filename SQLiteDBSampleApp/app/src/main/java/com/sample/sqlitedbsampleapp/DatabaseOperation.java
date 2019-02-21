package com.sample.sqlitedbsampleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseOperation {

    public static DatabaseOperation Instance;
    private DatabaseHelper dbHelper;

    private DatabaseOperation(Context context)
    {
        dbHelper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public static DatabaseOperation getInstance(Context context)
    {
        if(Instance == null)
        {
            Instance = new DatabaseOperation(context);
        }

        return Instance;
    }

    public long addContact(Contact contact)
    {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.CONTACT_NAME, contact.getName());
        contentValues.put(dbHelper.CONTACT_NUMBER, contact.getNumber());

        long returnId = sqLiteDatabase.insert(dbHelper.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

        return returnId;
    }

    public ArrayList<Contact> getAllContact()
    {
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+dbHelper.TABLE_NAME,null);
        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                Contact contact = new Contact();

                contact.setId(cursor.getInt(cursor.getColumnIndex(dbHelper.CONTACT_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(dbHelper.CONTACT_NAME)));
                contact.setNumber(cursor.getString(cursor.getColumnIndex(dbHelper.CONTACT_NUMBER)));

                contactArrayList.add(contact);
            }
        }

        sqLiteDatabase.close();
        return contactArrayList;
    }

    public boolean deleteContact(Contact contact)
    {
        boolean isDataDeleted = false;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        int retValue = sqLiteDatabase.delete(dbHelper.TABLE_NAME, dbHelper.CONTACT_ID+"="+contact.getId(), null);

        if(retValue > 0)
            isDataDeleted = true;

        sqLiteDatabase.close();
        return isDataDeleted;
    }

    public boolean updateContact(Contact contact)
    {
        boolean isDataUpdated = false;

        if(contact != null && contact.getId() > 0) {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.CONTACT_NAME, contact.getName());
            contentValues.put(dbHelper.CONTACT_NUMBER, contact.getNumber());
            int retValue = sqLiteDatabase.update(dbHelper.TABLE_NAME, contentValues, dbHelper.CONTACT_ID + "=" + contact.getId(), null);

            if (retValue > 0)
                isDataUpdated = true;
        }

        return isDataUpdated;
    }
}
