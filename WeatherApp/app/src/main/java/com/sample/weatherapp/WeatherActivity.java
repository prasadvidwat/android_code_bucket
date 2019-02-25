package com.sample.weatherapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.sample.weatherapp.R.id.data_fragment;

public class WeatherActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener, AdapterView.OnItemSelectedListener {

    String prevCity, prevDataCategory;
    WeatherDataFragment weatherDataFragment;
    Spinner citySpinner, dataCategorySpinner;
    ProgressBar progressBar;
    ArrayList<WeatherData> weatherDataList = new ArrayList<>();

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        progressBar = findViewById(R.id.progress_circular);
        citySpinner = findViewById(R.id.spinner_city);
        dataCategorySpinner = findViewById(R.id.spinner_data_category);

        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(this, R.array.cities_array, android.R.layout.simple_spinner_item);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(arrayAdapter1);

        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.data_category_array, android.R.layout.simple_spinner_item);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataCategorySpinner.setAdapter(arrayAdapter2);

        gson = new GsonBuilder().setPrettyPrinting().create();

        citySpinner.setOnItemSelectedListener(this);
        dataCategorySpinner.setOnItemSelectedListener(this);

        weatherDataFragment = new WeatherDataFragment();
        loadFragment(R.id.data_fragment, weatherDataFragment);
    }

    private void loadFragment(int viewIdToReplace, Fragment fragment)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment currentFragment = manager.findFragmentById(R.id.data_fragment);

        if(currentFragment != null) {
            transaction.detach(currentFragment);
            transaction.attach(fragment);
        } else {
            transaction.replace(viewIdToReplace, fragment);
        }
        transaction.commit();
    }

    public void makeRequest(String city, String dataCategory) {

        requestIsInProgress(true);
        String url = NetworkManager.getInstance(this).getBaseUrl();
        url += dataCategory + "-" + city + ".json";

        Request request = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        NetworkManager.getInstance(this).sendRequest(request);

    }

    @Override
    public void onResponse(Object response) {

        weatherDataList.clear();
        Type collectionType = new TypeToken<Collection<WeatherData>>(){}.getType();
        weatherDataList = gson.fromJson(response.toString(), collectionType);

        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i < weatherDataList.size(); i++)
        {
            stringBuilder.append(weatherDataList.get(i).toString());
        }

        requestIsInProgress(false);
        weatherDataFragment.updateFragment(weatherDataList);
    }

    private void requestIsInProgress(boolean inProgress)
    {
        if(inProgress)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String city = citySpinner.getSelectedItem().toString();
        String dataCategory = dataCategorySpinner.getSelectedItem().toString();

        if(true || (prevCity == null || prevDataCategory == null) || (!(prevCity.equals(city) || prevDataCategory.equals(dataCategory)))) {
            makeRequest(city, dataCategory);
            prevCity = city;
            prevDataCategory = dataCategory;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
