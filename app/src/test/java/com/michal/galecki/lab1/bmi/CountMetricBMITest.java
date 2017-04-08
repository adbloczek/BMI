package com.michal.galecki.lab1.bmi;

import org.junit.Assert;
import org.junit.Test;

public class CountMetricBMITest {
    @Test
    public void MassUnderZeroIsInvalid() throws Exception {
        float testMass = -1.0f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertFalse(actual);
    }

    @Test
    public void MassUnderMinimumValueIsInvalid() throws Exception {
        float testMass = 9.9f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertFalse(actual);
    }

    @Test
    public void MassAboveMaximumValueIsInvalid() throws Exception {
        float testMass = 250.1f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertFalse(actual);
    }

    @Test
    public void MassBetweenMinimumAndMaximumValueIsValid() throws Exception {
        float testMass = 15.0f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertTrue(actual);
    }

    @Test
    public void minimumMassIsValid() throws Exception {
        float testMass = 10.0f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertTrue(actual);
    }

    @Test
    public void maximumMassIsValid() throws Exception {
        float testMass = 250.0f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isMassValid(testMass);
        Assert.assertTrue(actual);
    }

    @Test
    public void heightUnderZeroIsInvalid() throws Exception {
        float testHeight = -1.0f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertFalse(actual);
    }

    @Test
    public void heightUnderMinimumValueIsInvalid() throws Exception {
        float testHeight = 0.49f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertFalse(actual);
    }

    @Test
    public void heightAboveMaximumValueIsInvalid() throws Exception {
        float testHeight = 2.51f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertFalse(actual);
    }

    @Test
    public void heightBetweenMinimumAndMaximumValueIsValid() throws Exception {
        float testHeight = 1.5f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertTrue(actual);
    }

    @Test
    public void minimumHeightIsValid() throws Exception {
        float testHeight = 0.5f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertTrue(actual);
    }

    @Test
    public void maximumHeightIsValid() throws Exception {
        float testHeight = 2.5f;
        CountMetricBMI bmi = new CountMetricBMI();
        boolean actual = bmi.isHeightValid(testHeight);
        Assert.assertTrue(actual);
    }

    @Test
    public void bmiWithValidArgumentsIsCountedProperly() throws Exception {
        float testMass = 85.0f;
        float testHeight = 1.8f;
        CountMetricBMI bmi = new CountMetricBMI();
        float actual = bmi.countBMI(testMass, testHeight);
        Assert.assertEquals(26.2345679, actual, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bmiWithInvalidArgumentsThrowsException() {
        float testMass = 0.0f;
        float testHeight = 0.0f;
        CountMetricBMI bmi = new CountMetricBMI();
        bmi.countBMI(testMass, testHeight);
    }
}
