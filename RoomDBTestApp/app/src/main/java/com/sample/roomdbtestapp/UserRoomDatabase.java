package com.sample.roomdbtestapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by prasad.vidwat on 15/12/18.
 */

@Database(entities = {User.class}, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {

    private static UserRoomDatabase instance;

    public abstract UserDao userDao();

    public static UserRoomDatabase getInstance(final Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserRoomDatabase.class, "user-database").
                    allowMainThreadQueries().build();
        }

        return instance;
    }

    public static void destroyInstance()
    {
        instance = null;
    }
}
