package com.sample.weatherapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataFragment extends Fragment {

    Context appContext;
    RecyclerView dataRecyclerView;
    ArrayList<WeatherData> weatherDataList;

    public WeatherDataFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weather_fragment_layout, null);
        dataRecyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateFragment(weatherDataList);
    }

    public void updateFragment(ArrayList<WeatherData> weatherData)
    {
        if(weatherData != null && weatherData.size() > 0) {
            DataRecyclerAdapter dataRecyclerAdapter = new DataRecyclerAdapter(this.getActivity().getApplicationContext(), weatherData);
            dataRecyclerView.setAdapter(dataRecyclerAdapter);
            dataRecyclerView.setLayoutManager(new LinearLayoutManager(appContext));
            dataRecyclerAdapter.notifyDataSetChanged();
        }
    }
}
