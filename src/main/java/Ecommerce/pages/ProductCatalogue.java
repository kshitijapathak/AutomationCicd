package Ecommerce.pages;

import org.openqa.selenium.WebElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Ecommerce.reusablecomponenets.AbstractComponent;



public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	

	
	By productsBy= By.cssSelector(".mb-3");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMessage= By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy, 10);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod= getProductList().stream().filter(product -> 
        product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
        .orElse(null);
		return prod;
	}
	
	 public boolean addProductToCart(String productName) throws InterruptedException {
	        WebElement prod = getProductByName(productName);
	        
	        if (prod == null) {
	            System.out.println("Product not found: " + productName);
	            return false; // Product not found, return false
	        }
	        
	        // Use an explicit wait instead of Thread.sleep
	        waitForElementToBeClickable(prod.findElement(addToCart), 10);
	        prod.findElement(addToCart).click();
	        
	        waitForElementToAppear(toastMessage, 10);
	        waitForElementToDisappear(spinner, 10);
	        
	        return true; // Successfully added to cart
	    }
	
	
	
	
}
