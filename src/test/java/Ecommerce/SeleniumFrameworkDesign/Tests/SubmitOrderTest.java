package Ecommerce.SeleniumFrameworkDesign.Tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;

import Ecommerce.pages.CartPage;
import Ecommerce.pages.CheckOutPage;
import Ecommerce.pages.ConfirmationPage;
import Ecommerce.pages.LandingPage;
import Ecommerce.pages.OrderPage;
import Ecommerce.pages.ProductCatalogue;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameworkDesign.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
	
	@Test(dataProvider="getData", groups={"Purchase"})
	public void StandAloneTest(HashMap<String, String> input) throws IOException, InterruptedException {
	    ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"), input.get("password"));
	    
	    // Ensure the product list is loaded
	    List<WebElement> products = productCatalogue.getProductList();
	    Assert.assertFalse(products.isEmpty(), "Product list is empty. Ensure products are available.");

	    // Attempt to add the product to the cart
	    boolean productAdded = productCatalogue.addProductToCart(input.get("product"));
	    Assert.assertTrue(productAdded, "Failed to add product to cart: " + input.get("product"));

	    CartPage cartPage = productCatalogue.goToCartPage();
	    Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
	    Assert.assertTrue(match, "Product not displayed in cart: " + input.get("product"));

	    CheckOutPage checkoutPage = cartPage.goToCheckout();
	    checkoutPage.selectCountry("india");
	    ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	    String confirmMessage = confirmationPage.getConfirmationMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."), "Confirmation message mismatch.");
	}

	//Dependency order - history test
	@Test(dependsOnMethods= {"StandAloneTest()"})
	public void OrderHistoryTest() 
	{
		ProductCatalogue productCatalogue = landingPage.LoginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
    public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "kshitijapathak172@gmail.com");
//		map.put("password", "Ssmaka17#");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String,String>>data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//seleniumFrameworkDesign//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
	
//	@DataProvider
//    public Object[][] getData()
//	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "kshitijapathak172@gmail.com");
//		map.put("password", "Ssmaka17#");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map},{map1}};
//		}
		
}




