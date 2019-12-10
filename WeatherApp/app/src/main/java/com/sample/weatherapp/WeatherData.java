package com.sample.weatherapp;

import java.io.Serializable;

// POJO class holding whether data 

public class WeatherData implements Serializable {

    private int month;
    private int year;
    private float value;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "month=" + month +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
