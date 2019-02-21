package com.sample.roomdbtestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ShowDbRecordsActivity extends AppCompatActivity {

    ListView userRecordListView;
    UserRecordAdapter userRecordAdapter;
    public List<User> userList;
    Spinner querySpinner;

    UserRoomDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_db_records);

        userRecordListView = (ListView)findViewById(R.id.list_view);
        querySpinner = (Spinner)findViewById(R.id.query_spinner);

        database = UserRoomDatabase.getInstance(this);
        userList = database.userDao().getAllUsers();

        userRecordAdapter = new UserRecordAdapter(getApplicationContext(), R.layout.user_item_layout, new ArrayList<User>());
        userRecordListView.setAdapter(userRecordAdapter);

        querySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        changeData(database.userDao().getAllUsers());
                        break;
                    case 1:
                        changeData(database.userDao().getPerticularUser("prasad"));
                        break;
                    case 2:
                        changeData(database.userDao().getUser("pra"));
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void changeData(List<User> newUserList)
    {
        userRecordAdapter.clear();
        userRecordAdapter.addAll(newUserList);
    }
}
