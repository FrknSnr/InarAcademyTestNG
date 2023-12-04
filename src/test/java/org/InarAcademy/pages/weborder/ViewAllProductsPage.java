package org.InarAcademy.pages.weborder;

import org.InarAcademy.pages.BasePage;
import org.InarAcademy.utils.ReusableMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewAllProductsPage extends BasePage {

    @FindBy(xpath = "(//td)")
    private List<WebElement> sections;

    @FindBy(xpath = "//h1[text()='View All Products']")
    private WebElement viewAllProductsText;

    public ViewAllProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getViewAllProductsText() {
        return viewAllProductsText.getText();
    }

    public int getNumberOfNames() {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < sections.size()-2; i += 3) {
            nameList.add(sections.get(i).getText());
        }
        return nameList.size();
    }

    public int getNumberOfPrices() {
        List<Integer> priceList = new ArrayList<>();
        for (int i = 1; i < sections.size()-1; i += 3) {
            int price = Integer.parseInt(ReusableMethods.getSubstringOfText(sections.get(i).getText(), 2, (sections.get(i).getText().length())));
            if (price > 0) {
                priceList.add(price);
            }
        }
        return priceList.size();
    }

    public HashMap<String, Integer> getProductDiscountMap() {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < sections.size() - 2; i += 3) {
            String productName = sections.get(i).getText();
            int discount = Integer.parseInt(ReusableMethods.getSubstringOfText(sections.get(i + 2).getText(), 0, (sections.get(i + 2).getText().length() - 2)));
            map.put(productName, discount);
        }
        return map;
    }
}