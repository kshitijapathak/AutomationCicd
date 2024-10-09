package stepDefinations;

import java.io.IOException;

import org.testng.Assert;

import Ecommerce.pages.CartPage;
import Ecommerce.pages.CheckOutPage;
import Ecommerce.pages.ConfirmationPage;
import Ecommerce.pages.LandingPage;
import Ecommerce.pages.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameworkDesign.TestComponents.BaseTest;

public class StepDefinationImp extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        productCatalogue = landingPage.LoginApplication(username, password);
    }

    @When("^I add product (.+) to cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        productCatalogue.addProductToCart(productName);
    }

    @When("^checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) {
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("\"THANKYOU FOR THE ORDER.\" message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage() {
        String expectedMessage = "THANKYOU FOR THE ORDER.";
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(expectedMessage));
        driver.close();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
        Assert.assertEquals(strArg1, landingPage.getErrorMessage());
        driver.close();
    }
}
