package org.InarAcademy.dataProvider;

import org.InarAcademy.utils.ReusableMethods;
import org.testng.annotations.DataProvider;

public class UsernameData {

    @DataProvider(name = "usernameData")
    public Object[][] usernameData() {
        String csvFilePath = "src/main/java/resources/usernameDatas.csv";
        return ReusableMethods.getCsvData(csvFilePath, ",");
    }
}