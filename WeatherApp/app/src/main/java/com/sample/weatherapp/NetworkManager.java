package com.sample.weatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NetworkManager {

    private static NetworkManager instance;
    private RequestQueue mRequestQueue;
    private Context appContext;

    private String baseUrl = "https://s3.eu-west-2.amazonaws.com/interview-question-data/metoffice/";

    private NetworkManager(Context context) {
        appContext = context;
        mRequestQueue = Volley.newRequestQueue(appContext);
    }

    public static NetworkManager getInstance(Context context) {

        if(instance == null)
            instance = new NetworkManager(context);

        return instance;
    }

    public void sendRequest(Request request)
    {
        mRequestQueue.add(request);
    }

    public void removeAllRequests()
    {
        mRequestQueue.cancelAll(null);
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
