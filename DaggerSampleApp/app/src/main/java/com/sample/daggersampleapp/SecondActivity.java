package com.sample.daggersampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

public class SecondActivity extends AppCompatActivity {

    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        CarComponent carComponent = DaggerCarComponent.create();
        carComponent.injectSecondAcivity(this);

        car.drive();
    }
}
