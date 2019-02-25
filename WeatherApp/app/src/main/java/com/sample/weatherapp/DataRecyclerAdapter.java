package com.sample.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DataRecyclerAdapter extends RecyclerView.Adapter<DataRecyclerAdapter.DataViewHolder> {

    public class DataViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtMonth, txtYear, txtValue;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMonth = itemView.findViewById(R.id.txt_month);
            txtYear = itemView.findViewById(R.id.txt_year);
            txtValue = itemView.findViewById(R.id.txt_value);
        }

        public void setData(WeatherData weatherData)
        {
            txtMonth.setText(Integer.toString(weatherData.getMonth()));
            txtYear.setText(Integer.toString(weatherData.getYear()));
            txtValue.setText(Float.toString(weatherData.getValue()));
        }
    }

    private Context appContext;
    private List<WeatherData> weatherDataList;

    public DataRecyclerAdapter(Context context, ArrayList<WeatherData> weatherDataList) {

        this.appContext = context;
        this.weatherDataList = weatherDataList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(appContext).inflate(R.layout.weather_data_item, viewGroup, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder viewHolder, int i) {

        WeatherData weatherData = weatherDataList.get(i);
        viewHolder.setData(weatherData);
    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }
}
