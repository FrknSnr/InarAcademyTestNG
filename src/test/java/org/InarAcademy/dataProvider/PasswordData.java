package org.InarAcademy.dataProvider;

import org.InarAcademy.utils.ReusableMethods;
import org.testng.annotations.DataProvider;

public class PasswordData {

    @DataProvider(name = "passwordData")
    public Object[][] passwordData() {
        String csvFilePath = "src/main/java/resources/passwordDatas.csv";
        return ReusableMethods.getCsvData(csvFilePath, ",");
    }
}