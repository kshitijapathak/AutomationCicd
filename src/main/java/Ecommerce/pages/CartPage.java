package Ecommerce.pages;



import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Ecommerce.reusablecomponenets.AbstractComponent;


public class CartPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}
	
	  public CheckOutPage goToCheckout() {
	        // Wait for the checkout element to be clickable
	        waitForElementToBeClickable(checkoutEle, 10);

	        // Optional: Scroll to the checkout element to ensure it's in view
	        scrollToElement(checkoutEle);

	        // Try clicking using JavaScript toavoid click interception
	        clickUsingJavaScript(checkoutEle);

	        return new CheckOutPage(driver);
	    }
	

}