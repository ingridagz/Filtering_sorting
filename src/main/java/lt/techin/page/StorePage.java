package lt.techin.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StorePage extends BasePage {
    public StorePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "select#page-menu")
    WebElement pageSizeButton;
    @FindBy(xpath = "//span[.='Veg/fruit name']")
    WebElement productNameButton;

    @FindBy(xpath = "//table//tbody//tr/td[1]")
    List<WebElement> productElements;

    @FindBy(css = "input#search-field")
    WebElement searchProduct;

    public void clickSizeButton() {
        pageSizeButton.click();
    }
    public void selectText(String text) {
        Select select = new Select(pageSizeButton);
        select.selectByVisibleText(text);
    }
    public void clickProductNameButton() {
        productNameButton.click();
    }
    public ArrayList<String> getProductNames() {
        ArrayList<String> productNames = new ArrayList<>();
        for (WebElement productElement : productElements) {
            productNames.add(productElement.getText());
            System.out.println(productElement.getText());
        }
        return productNames;
    }
    public ArrayList<String> sortProductNames(ArrayList<String> productNames) {
        ArrayList<String> sortedList = new ArrayList<>(productNames);
        Collections.sort(sortedList);
        System.out.println(sortedList);
        return sortedList;
    }
    public void setSearchProduct(String input) {
        searchProduct.sendKeys(input);
        System.out.println("Search result: " + searchProduct.getAttribute("value"));
    }

}
