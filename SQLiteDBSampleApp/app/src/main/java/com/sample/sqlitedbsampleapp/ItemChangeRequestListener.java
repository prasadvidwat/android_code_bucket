package com.sample.sqlitedbsampleapp;

public interface ItemChangeRequestListener {

    void onDataUpdate(Contact data);

    void onDataDelete(Contact contact);
}
