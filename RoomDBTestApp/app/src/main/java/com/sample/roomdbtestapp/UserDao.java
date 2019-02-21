package com.sample.roomdbtestapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by prasad.vidwat on 15/12/18.
 */

@Dao
public interface UserDao {

    @Insert
    public void addUser(User user);

    @Delete
    public void deleteUser(User user);

    @Update
    public void updateUser(User user);

    @Query("Select * from user_table")
    public List<User> getAllUsers();

    @Query("Select * from user_table where user_name = :name")
    public List<User> getPerticularUser(String name);

    @Query("Select * from user_table where user_name like :name")
    public List<User> getUser(String name);

}
