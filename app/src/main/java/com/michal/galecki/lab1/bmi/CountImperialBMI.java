package com.michal.galecki.lab1.bmi;

import android.content.Context;

public class CountImperialBMI implements ICountBMI {
    final float MIN_MASS = 22.04f;
    final float MAX_MASS = 551.0f;
    final float MIN_HEIGHT = 1.64f;
    final float MAX_HEIGHT = 8.2f;

    @Override
    public boolean isMassValid(float mass) {
        return mass >= MIN_MASS && mass <= MAX_MASS;
    }

    @Override
    public boolean isHeightValid(float height) {
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT;
    }

    @Override
    public float countBMI(float mass, float height) {
        if (isMassValid(mass) && isHeightValid(height)) {
            return (mass * 4.88f) / (height * height);
        } else {
            throw new IllegalArgumentException("Invalid data");
        }
    }

    public String getInvalidDataDescription(Context context) {
        return context.getString(R.string.all_mass) + " " + context.getString(R.string.have_to_be_between) + " " + MIN_MASS + " " + context.getString(R.string.and) + " " + MAX_MASS + " " + context.getString(R.string.pounds) +"\n" +
                context.getString(R.string.all_height) + " " + context.getString(R.string.have_to_be_between) + " " + MIN_HEIGHT + " " + context.getString(R.string.and) + " " + MAX_HEIGHT + " " + context.getString(R.string.feet);
    }
}
