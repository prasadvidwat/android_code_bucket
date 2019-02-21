package com.sample.daggersampleapp;

import dagger.Component;

@Component
public interface CarComponent {

    Car getCar();

    void injectMainAcivity(MainActivity mainActivity);
    void injectSecondAcivity(SecondActivity secondActivity);
}
