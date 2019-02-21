package com.sample.roomdbtestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addUserButton, showUserButton;
    EditText etxUserName, etxUserContact;
    UserRoomDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUserButton = (Button) findViewById(R.id.add_user_button);
        showUserButton = (Button) findViewById(R.id.show_user_button);

        etxUserName = (EditText) findViewById(R.id.user_name);
        etxUserContact = (EditText) findViewById(R.id.user_contact);

        addUserButton.setOnClickListener(this);
        showUserButton.setOnClickListener(this);

        database = UserRoomDatabase.getInstance(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add_user_button)
        {
            String userName = etxUserName.getText().toString();
            String userContact = etxUserContact.getText().toString();

            if(userName != null && userContact != null)
            {
                addUserToDb(userName, userContact);
                etxUserName.setText("");
                etxUserContact.setText("");
            } else {
                Log.d(getString(R.string.app_name),"User data is null");
            }

        } else {
            startActivity(new Intent(getApplicationContext(), ShowDbRecordsActivity.class));
        }
    }

    private void addUserToDb(String userName, String userContact)
    {
        User user = new User();
        user.setName(userName);
        user.setContact(userContact);

        database.userDao().addUser(user);
    }
}
