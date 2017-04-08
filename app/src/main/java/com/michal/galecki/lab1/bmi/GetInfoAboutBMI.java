package com.michal.galecki.lab1.bmi;

import android.graphics.Color;

public class GetInfoAboutBMI {
    public int getColorOfBMI(float bmi_value) {
        if (bmi_value < 16) {
            return Color.rgb(85,85,255);
        } else if (bmi_value < 17) {
            return Color.rgb(119,119,255);
        } else if (bmi_value < 18.5) {
            return Color.rgb(153,153,255);
        } else if (bmi_value < 25) {
            return Color.rgb(187,255,187);
        } else if (bmi_value < 30) {
            return Color.rgb(255, 187, 187);
        } else if (bmi_value < 35) {
            return Color.rgb(255,153,153);
        } else if (bmi_value < 40) {
            return Color.rgb(255,119,119);
        } else {
            return Color.rgb(255,85,85);
        }
    }

    public int getDescriptionAboutBMI(float bmi_value) {
        if (bmi_value < 16) {
            return R.string.very_severely_underweight;
        } else if (bmi_value < 17) {
            return R.string.severely_underweight;
        } else if (bmi_value < 18.5) {
            return R.string.underweight;
        } else if (bmi_value < 25) {
            return R.string.normal_weight;
        } else if (bmi_value < 30) {
            return R.string.overweight;
        } else if (bmi_value < 35) {
            return R.string.obese_first_class;
        } else if (bmi_value < 40) {
            return R.string.obese_second_class;
        } else {
            return R.string.obese_third_class;
        }
    }
}
