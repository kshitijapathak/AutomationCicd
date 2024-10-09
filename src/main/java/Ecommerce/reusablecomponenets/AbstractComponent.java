package Ecommerce.reusablecomponenets;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Ecommerce.pages.CartPage;
import Ecommerce.pages.OrderPage;

public class AbstractComponent {
    
    private WebDriver driver;
    private final int DEFAULT_TIMEOUT = 15;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(css = "[routerlink*= 'cart']")
    private WebElement cartHeader;
    
    @FindBy(css = "[routerlink*= 'myorders']")
    private WebElement orderHeader;

    public void waitForElementToAppear(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public CartPage goToCartPage() {
        clickElement(cartHeader);
        return new CartPage(driver);
    }
    
    public OrderPage goToOrdersPage() {
        clickElement(orderHeader);
        return new OrderPage(driver);
    }
    
    public void waitForElementToDisappear(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    
    public void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public void scrollToElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element)); // Wait until the element is visible
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickUsingJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void clickElement(WebElement element) {
        try {
            waitForElementToBeClickable(element, DEFAULT_TIMEOUT); // Default timeout for click
            scrollToElement(element); // Scroll to the element first
            element.click(); // Try clicking normally
        } catch (Exception e) {
            // Log exception if needed
            System.out.println("Failed to click on the element: " + e.getMessage());
            clickUsingJavaScript(element); // Fallback to JavaScript click if normal click fails
        }
    }
    
    public void waitForWebElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
