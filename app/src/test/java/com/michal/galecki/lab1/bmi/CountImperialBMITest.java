package com.michal.galecki.lab1.bmi;

import org.junit.Assert;
import org.junit.Test;

public class CountImperialBMITest {
    @Test
    public void MassUnderZeroIsInvalid() throws Exception {
        float testMass = -1.0f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertFalse(actual);
    }

    @Test
    public void MassUnderMinimumValueIsInvalid() throws Exception {
        float testMass = 9.9f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertFalse(actual);
    }

    @Test
    public void MassAboveMaximumValueIsInvalid() throws Exception {
        float testMass = 552.1f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertFalse(actual);
    }

    @Test
    public void MassBetweenMinimumAndMaximumValueIsValid() throws Exception {
        float testMass = 25.0f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertTrue(actual);
    }

    @Test
    public void minimumMassIsValid() throws Exception {
        float testMass = 22.04f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertTrue(actual);
    }

    @Test
    public void maximumMassIsValid() throws Exception {
        float testMass = 551.0f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertTrue(actual);
    }

    @Test
    public void heightUnderZeroIsInvalid() throws Exception {
        float testHeight = -1.0f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertFalse(actual);
    }

    @Test
    public void heightUnderMinimumValueIsInvalid() throws Exception {
        float testHeight = 0.49f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertFalse(actual);
    }

    @Test
    public void heightAboveMaximumValueIsInvalid() throws Exception {
        float testHeight = 12.51f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertFalse(actual);
    }

    @Test
    public void heightBetweenMinimumAndMaximumValueIsValid() throws Exception {
        float testHeight = 5.5f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertTrue(actual);
    }

    @Test
    public void minimumHeightIsValid() throws Exception {
        float testHeight = 1.64f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertTrue(actual);
    }

    @Test
    public void maximumHeightIsValid() throws Exception {
        float testHeight = 8.2f;
        CountImperialBMI bmi = new CountImperialBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertTrue(actual);
    }

    @Test
    public void bmiWithValidArgumentsIsCountedProperly() throws Exception {
        float testMass = 185.0f;
        float testHeight = 5.8f;
        CountImperialBMI bmi = new CountImperialBMI();
        float actual = bmi.countBMI(testMass, testHeight);
        Assert.assertEquals(26.8370986, actual, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bmiWithInvalidArgumentsThrowsException() {
        float testMass = 0.0f;
        float testHeight = 0.0f;
        CountImperialBMI bmi = new CountImperialBMI();
        bmi.countBMI(testMass, testHeight);
    }
}
