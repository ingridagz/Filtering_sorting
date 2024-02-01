package lt.techin;

import lt.techin.page.StorePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class StoreTest extends BaseTest {
    private StorePage storePage;
    private String input = "an";
    private String text="20";

    @BeforeEach
    void setUp() {
        storePage = new StorePage(driver);
    }

    @Test
    void sortProduct() {
        storePage.clickSizeButton();
        storePage.selectText(text);
        storePage.clickProductNameButton();
        ArrayList<String> productNames = storePage.getProductNames();
        ArrayList<String> sortedList = storePage.sortProductNames(productNames);

        Assertions.assertArrayEquals(productNames.toArray(), sortedList.toArray());
    }

    @Test
    void searchProduct() {
        storePage.setSearchProduct(input);
        ArrayList<String> productNames = storePage.getProductNames();

        for (String productName : productNames) {
            Assertions.assertTrue(productName.toLowerCase().contains(input));
        }
    }
}
