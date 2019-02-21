package com.sample.jsonparsingusinggsonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final private String TAG = "MainActivity";
    private TextView textView;
    private Button btnJson, btnGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.response);
        btnJson = findViewById(R.id.btn_json);
        btnGson = findViewById(R.id.btn_gson);

        btnJson.setOnClickListener(this);
        btnGson.setOnClickListener(this);

    }

    // This method will parse the JSON data using GSON third party library
    private String parseJsonDataUsingGson() throws IOException {
        String dataParsed = "";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonData = loadData();
        UserDataModel userDataModel = gson.fromJson(jsonData, UserDataModel.class);

        return userDataModel.toString();
    }

    // This method will parse the JSON data using json classes provide by android os itself
    private String parseJsonData()
    {
        List<UserModel> userModelList = new ArrayList<>();
        String dataParsed = "";

        try {
            String jsonData = loadData();

            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = (JSONArray) jsonObject.get("userdata");

            for(int i=0; i < jsonArray.length(); i++)
            {
                UserModel userModel = new UserModel();
                UserAddress userAddress = new UserAddress();
                BirthDate birthDate = new BirthDate();

                // User info
                JSONObject userObject = (JSONObject) jsonArray.get(i);

                // User personal info
                userModel.setUserId((Integer) userObject.get("id"));
                userModel.setName((String) userObject.get("name"));
                //userModel.setPhone((String) userObject.get("phone"));

                // User birthday info
                JSONObject birthdateObject = (JSONObject) userObject.get("bday");
                birthDate.setDay((Integer) birthdateObject.get("day"));
                birthDate.setMonth((Integer) birthdateObject.get("month"));
                birthDate.setYear((Integer) birthdateObject.get("year"));
                userModel.setBday(birthDate);

                // User address info
                JSONObject addressObject = (JSONObject) userObject.get("address");
                userAddress.setCity((String) addressObject.get("city"));
                userAddress.setTel((String) addressObject.get("tel"));
                userAddress.setDist((String) addressObject.get("dist"));
                userAddress.setPin((Integer) addressObject.get("pin"));
                userModel.setAddress(userAddress);

                Log.d(TAG, "parseJsonData: "+userModel+"\n");
                dataParsed += userModel.toString();

                dataParsed += "\n-------------------\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dataParsed;
    }

    String loadData() throws IOException {

        String fileData = "";

        InputStream inputStream = getAssets().open("sample_json.json");
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        fileData = new String(buffer, "UTF-8");

        return fileData;
    }

    @Override
    public void onClick(View view) {

        String dataParsed = "";

        if(view.getId() == R.id.btn_json) {
            dataParsed = parseJsonData();
        } else {
            try {
                dataParsed = parseJsonDataUsingGson();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        textView.setText(dataParsed);
    }
}
