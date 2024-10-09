package Ecommerce.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Ecommerce.reusablecomponenets.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".action__submit")
    WebElement submit;

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])")
    List<WebElement> countryOptions; // Use this to find country options

    By results = By.cssSelector(".ta-results.list-group.ng-star-inserted");

    public void selectCountry(String countryName) {
        // Click to focus on the input field
        country.click();
        
        // Clear the field and type the country name
        country.clear(); // Ensure any existing text is cleared

        // Use Actions to type the country name with a small pause
        Actions actions = new Actions(driver);
        actions.sendKeys(country, countryName).perform();
        
        // Optionally wait a bit before checking for results
        try {
            Thread.sleep(500); // Sleep for a short duration (adjust as necessary)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Wait for the results to appear
        waitForElementToAppear(results, 10);
        
        // Refresh countryOptions after waiting for results
        countryOptions = driver.findElements(By.xpath("//button[contains(@class,'ta-item')]"));

        // Check if country options are displayed and select the desired one
        boolean countryFound = false;
        for (WebElement option : countryOptions) {
            if (option.getText().equalsIgnoreCase(countryName)) {
                option.click();
                countryFound = true;
                break; // Click the desired country and exit the loop
            }
        }

        if (!countryFound) {
            throw new RuntimeException("Country not found: " + countryName);
        }
    }

    public ConfirmationPage submitOrder() {
        // Wait for the button to be clickable
        waitForElementToBeClickable(submit, 10); // Using the method from AbstractComponent

        // Scroll to the button to ensure it is in view
        scrollToElement(submit); // Using the method from AbstractComponent

        // Attempt to click the button using a standard click method
        try {
            submit.click(); // Attempt to click the submit button normally
        } catch (ElementClickInterceptedException e) {
            // If intercepted, try clicking using JavaScript
            System.out.println("Element click intercepted, trying JavaScript click.");
            clickUsingJavaScript(submit); // Using the method from AbstractComponent
        }

        return new ConfirmationPage(driver);
    }

}
