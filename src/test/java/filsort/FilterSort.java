package filsort;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterSort {

    public static void palaukti() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    WebDriver driver;

    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    void afterEach() {
//        driver.quit();
    }

    @Test
    void sortProduct() {

        WebElement pageSizeButton = driver.findElement(By.cssSelector("select#page-menu"));
        pageSizeButton.click();

        Select select = new Select(pageSizeButton);
        select.selectByVisibleText("20");

        WebElement productNameButton = driver.findElement(By.xpath("//span[.='Veg/fruit name']"));
        productNameButton.click();

        List<WebElement> productElements = driver.findElements(By.xpath("//table//tbody//tr/td[1]"));

        ArrayList<String> productNames = new ArrayList<>();

        for (WebElement productElement : productElements) {
            productNames.add(productElement.getText());
            System.out.println(productElement.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>(productNames);
        Collections.sort(sortedList);
        System.out.println(sortedList);

        Assertions.assertEquals(productNames, sortedList);
    }

    @Test
    void searchProduct() {

        String input = "an";
        WebElement searchProduct = driver.findElement(By.cssSelector("input#search-field"));
        searchProduct.sendKeys(input);
        System.out.println("Search result: " + searchProduct.getAttribute("value"));

        List<WebElement> productElements = driver.findElements(By.xpath("//table//tbody//tr/td[1]"));

        ArrayList<String> productNames = new ArrayList<>();

        for (WebElement productElement : productElements) {
            productNames.add(productElement.getText());
            System.out.println(productElement.getText());
        }

        for (String productName:productNames) {
            Assertions.assertTrue(productName.toLowerCase().contains(input));
        }
    }
}
