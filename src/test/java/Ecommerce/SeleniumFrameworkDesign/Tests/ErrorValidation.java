package Ecommerce.SeleniumFrameworkDesign.Tests;
import seleniumFrameworkDesign.TestComponents.Retry;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import Ecommerce.pages.CartPage;
import Ecommerce.pages.CheckOutPage;
import Ecommerce.pages.ConfirmationPage;
import Ecommerce.pages.ProductCatalogue;
import seleniumFrameworkDesign.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest
{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException 
	{
	    String productName = "ZARA COAT 3";
	    ProductCatalogue productcatalogue = landingPage.LoginApplication("kshitija@gmail.com", "Ssmaka17");
	    String actualErrorMessage = landingPage.getErrorMessage().trim();
	    System.out.println("Actual Error Message: " + actualErrorMessage); // Print the actual message for debugging
	    
	    Assert.assertEquals(actualErrorMessage, "Incorrect email and password.");
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.LoginApplication("rahulshetty@gmail.com", "Iamking@000");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	

	}
	
}
	
	
