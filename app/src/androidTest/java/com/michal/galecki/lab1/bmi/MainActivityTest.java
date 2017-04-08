package com.michal.galecki.lab1.bmi;

import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void massEditTextAcceptDigits() {
        float mass = 13.56f;
        onView(withId(R.id.edittext_main_height)).perform(clearText());
        onView(withId(R.id.edittext_main_mass)).perform(clearText());
        onView(withId(R.id.edittext_main_mass)).perform(typeText(String.valueOf(mass)));
        onView(withId(R.id.edittext_main_mass)).check(matches(withText(String.valueOf(mass))));
    }

    @Test
    public void massEditTextDoesntAcceptNonDigits() {
        String test = "Ab!@#$%^&*()";
        onView(withId(R.id.edittext_main_height)).perform(clearText());
        onView(withId(R.id.edittext_main_mass)).perform(clearText());
        onView(withId(R.id.edittext_main_mass)).perform(typeText(String.valueOf(test)));
        onView(withId(R.id.edittext_main_mass)).check(matches(withText("")));
    }

    @Test
    public void heightEditTextAcceptDigits() {
        float height = 1.86f;
        onView(withId(R.id.edittext_main_height)).perform(clearText());
        onView(withId(R.id.edittext_main_mass)).perform(clearText());
        onView(withId(R.id.edittext_main_height)).perform(typeText(String.valueOf(height)));
        onView(withId(R.id.edittext_main_height)).check(matches(withText(String.valueOf(height))));
    }

    @Test
    public void heightEditTextDoesntAcceptNonDigits() {
        onView(withId(R.id.edittext_main_height)).perform(clearText());
        onView(withId(R.id.edittext_main_mass)).perform(clearText());
        String test = "Ab!@#$%^&*()";
        onView(withId(R.id.edittext_main_height)).perform(typeText(String.valueOf(test)));
        onView(withId(R.id.edittext_main_height)).check(matches(withText("")));
    }

    @Test
    public void BMIResultIsDisplayedAfterEnteringValidData() {
        float mass = 13.56f;
        float height = 1.86f;
        onView(withId(R.id.edittext_main_height)).perform(clearText());
        onView(withId(R.id.edittext_main_mass)).perform(clearText());
        onView(withId(R.id.edittext_main_mass)).perform(typeText(String.valueOf(mass)));
        onView(withId(R.id.edittext_main_height)).perform(typeText(String.valueOf(height)));
        onView(withId(R.id.button_main_count)).perform(click());
        onView(withId(R.id.textview_bmi_result)).check(matches(isDisplayed()));
    }

    @Test
    public void singleClickedUnitsSwitchChangesUnitsToImperial() {
        onView(withId(R.id.switch_main_units)).perform(click());
        onView(withId(R.id.switch_main_units)).check(matches(withText(R.string.imperial_units)));
    }

    @Test
    public void doubleClickedUnitsSwitchChangesUnitsToMetric() {
        onView(withId(R.id.switch_main_units)).perform(click());
        onView(withId(R.id.switch_main_units)).perform(click());
        onView(withId(R.id.switch_main_units)).check(matches(withText(R.string.metric_units)));
    }
}

