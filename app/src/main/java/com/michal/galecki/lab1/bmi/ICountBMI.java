package com.michal.galecki.lab1.bmi;

public interface ICountBMI {
    boolean isMassValid(float mass);
    boolean isHeightValid(float height);
    float countBMI(float mass, float height);
}
