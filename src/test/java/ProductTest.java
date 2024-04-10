import com.automation.core.DriverManager;
import com.automation.core.ExtentReportManager;
import com.automation.core.PageObjectManager;
import com.automation.listeners.RetryAnalyzer;
import com.automation.pages.LoginScreen;
import com.automation.pages.ProductsScreen;
import com.automation.utils.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Objects;

/**
 * This class contains a test method to validate the maximum product price.
 *
 * Purpose of maxPrice:
 * - The static variable maxPrice is used to represent the expected maximum price of a product.
 * - It's initialized with an initial value of $51.99.
 * - During test execution, the maxPrice is decremented by 1 for each subsequent test run.
 * - This decrement is used to simulate a scenario where the expected maximum price decreases with each retry.
 * - Eventually, after 2 test runs (due to the retry analyzer), the maxPrice will reach $48.99.
 *
 * Purpose of the Test Method (validateMaxProductPrice):
 * - This method validates whether the maximum product price matches the expected maxPrice.
 * - It first logs in to the application to access the product screen.
 * - Then, it waits for some time before validating the maximum product price.
 * - The test method logs the current value of maxPrice and the expected max price for clarity.
 * - It logs the test result based on whether the max price validation is successful or not.
 * - Finally, it asserts the validation result to mark the test as pass or fail accordingly.
 */
public class ProductTest {

    private static double maxPrice = 51.99;

    @Test(priority = 150)
    public void validateMaxProductPrice() throws IOException {
        LoginTest loginTest = new LoginTest();
        loginTest.loginTest();

        PageObjectManager pageObjectManager = new PageObjectManager(DriverManager.getDriver());
        ProductsScreen productsScreen = pageObjectManager.getProductScreen();

        Utility.waitForSomeTime(3);
        boolean validatePrice = productsScreen.validateMaxProductPrice(maxPrice);

        // Decrement maxPrice for subsequent test runs
        maxPrice--;

        System.out.println("Max Price " +maxPrice);

        if (validatePrice) {
            ExtentReportManager.pass("Max price is matched. Expected: " + maxPrice);
        } else {
            ExtentReportManager.fail("Max price is not matched");
        }

        Assert.assertTrue(validatePrice);
    }
}
