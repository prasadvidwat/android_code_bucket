package com.sample.sqlitedbsampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    Button saveContactButton;
    EditText nameEditText, numberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        saveContactButton = (Button) findViewById(R.id.btn_save_contact);
        nameEditText = (EditText) findViewById(R.id.etxt_contact_name);
        numberEditText = (EditText) findViewById(R.id.etxt_contact_number);

        saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {

        String name = nameEditText.getText().toString();
        String phone = numberEditText.getText().toString();

        if(name.equals("") || phone.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please fill name and number field..", Toast.LENGTH_SHORT).show();
            return;
        }

        Contact contact = new Contact();
        contact.setName(name);
        contact.setNumber(phone);

        long retId = DatabaseOperation.getInstance(this).addContact(contact);

        if(retId < 0)
        {
            Toast.makeText(getApplicationContext(), "Something went wrong.. Contact may not be saved..", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Contact saved successfully with ID:"+retId, Toast.LENGTH_SHORT).show();
        }

        nameEditText.setText("");
        numberEditText.setText("");

    }
}
