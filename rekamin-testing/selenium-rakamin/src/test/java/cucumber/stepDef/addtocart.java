package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

import config.env;

public class addtocart extends env{

    @Given("user have to login first to atc")
    public void user_have_to_login_first_to_atc(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("user input (.*) as user_name to atc$")
    public void userInputUser_nameAsUser_nameToAtc(String user_name) {
        driver.findElement(By.id("user-name")).sendKeys(user_name);
    }

    @And("user input (.*) as password to atc$")
    public void userInputPasswordAsPasswordToAtc(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("user click submit to atc")
    public void userClickSubmitToAtc() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @And("user click Add to cart on all product")
    public void userClickAddToCartOnAllProduct() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        totalItemsAdded++;

        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        totalItemsAdded++;

        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        totalItemsAdded++;

        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        totalItemsAdded++;

        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        totalItemsAdded++;
    }

    @Then("product verify (.*) amount result$")
    public void productVerifyStatusAmountResult(String status) {
        if (status.equals("success")) {
            String addToCartAssert = driver.findElement(By.className("shopping_cart_badge")).getText();
            Assert.assertEquals(this.totalItemsAdded,Integer.parseInt(addToCartAssert));
        }
    }
}
