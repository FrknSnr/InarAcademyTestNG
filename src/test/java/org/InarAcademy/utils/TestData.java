package org.InarAcademy.utils;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "usernameData")
    public Object[][] usernameData() {
        return new Object[][]{
                {"Inr", "Academy", "Invalid username"},
                {"", "Academy", "Please enter your username"},
        };
    }

    @DataProvider(name = "passwordData")
    public Object[][] passwordData() {
        return new Object[][]{
                {"Inar", "Acdemy", "Invalid password"},
                {"Inar", "", "Please enter your password"},
        };
    }
}