package cucumber.stepDef;
import config.env;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.v85.dom.model.ShadowRootType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class checkout extends env{

    @Given("user have to login first to checkout")
    public void user_have_to_login_first_to_checkout (){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("user input (.*) as user_name to checkout$")
    public void userInputUser_nameAsUser_nameToCheckout(String user_name) {
        driver.findElement(By.id("user-name")).sendKeys(user_name);
    }

    @And("user input (.*) as password to checkout$")
    public void userInputPasswordAsPasswordToCheckout(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("user click submit to checkout")
    public void userClickSubmitToCheckout() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @And("user click Add to cart on all product to checkout")
    public void userClickAddToCartOnAllProductToCheckout() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
    }

    @And("user click cart button to checkout")
    public void userClickCartButtonToCheckout() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @And("user click checkout button")
    public void userClickCheckoutButton() {
        driver.findElement(By.id("checkout")).click();
    }

    @And("user input (.*) as first_name$")
    public void userInputFirst_nameAsFirst_name(String first_name) {
        driver.findElement(By.id("first-name")).sendKeys(first_name);
    }

    @And("user input (.*) as last_name$")
    public void userInputLast_nameAsLast_name(String last_name) {
        driver.findElement(By.id("last-name")).sendKeys(last_name);
    }

    @And("user input (.*) as postal_code$")
    public void userInputPostal_codeAsPostal_code(String postal_code) {
        driver.findElement(By.id("postal-code")).sendKeys(postal_code);
    }

    @And("user click continue button")
    public void userClickContinueButton() {
        driver.findElement(By.id("continue")).click();
    }

    @And("user click finish button")
    public void userClickFinishButton() {
        driver.findElement(By.id("finish")).click();
    }

    @Then("user verify (.*) checkout result$")
    public void userVerifyStatusCheckoutResult(String status) {
        if (status.equals("success")) {
            String checkoutPageAssert = driver.findElement(By.className("title")).getText();
            Assert.assertEquals("Checkout: Complete!",checkoutPageAssert);
        }
    }
}
