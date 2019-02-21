package com.sample.sqlitedbsampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemChangeRequestListener {

    ListView contactListView;
    Button addContyactButton;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactListView = (ListView) findViewById(R.id.list_view_show_data);
        addContyactButton = (Button) findViewById(R.id.btn_add_contact);

        contactAdapter = new ContactAdapter(this, R.layout.contact_item);
        contactListView.setAdapter(contactAdapter);

        addContyactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshData();
    }

    private void refreshData() {

        ArrayList<Contact> contactList = DatabaseOperation.getInstance(this).getAllContact();

        if(contactList != null && contactList.size() > 0)
        {
            contactAdapter.clear();
            contactAdapter.addAll(contactList);
            contactAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDataUpdate(Contact data) {

    }

    @Override
    public void onDataDelete(Contact contact) {
        DatabaseOperation.getInstance(this).deleteContact(contact);
        refreshData();
    }
}
