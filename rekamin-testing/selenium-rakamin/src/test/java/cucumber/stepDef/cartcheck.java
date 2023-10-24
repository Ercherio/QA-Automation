package cucumber.stepDef;

import config.env;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class cartcheck extends env {
    @Given("user have to login first to cart")
    public void user_have_to_login_first_to_cart(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("user input (.*) as user_name to cart$")
    public void userInputUser_nameAsUser_nameToCart(String user_name) {
        driver.findElement(By.id("user-name")).sendKeys(user_name);
    }

    @And("user input (.*) as password to cart$")
    public void userInputPasswordAsPasswordToCart(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("user click submit to cart")
    public void userClickSubmitToCart() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @And("user click Add to cart on all product to cart")
    public void userClickAddToCartOnAllProductToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        totalItemsAdded++;

        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        totalItemsAdded++;

        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        totalItemsAdded++;

        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        totalItemsAdded++;

        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        totalItemsAdded++;

        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        totalItemsAdded++;
    }

    @And("user click cart button")
    public void userClickCartButton() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @Then("user verify (.*) cart result$")
    public void userVerifyStatusCartResult(String status) {
        if (status.equals("success")) {
            String cartPageAssert = driver.findElement(By.className("title")).getText();
            Assert.assertEquals("Your Cart",cartPageAssert);

            // Temukan elemen yang memiliki class "cart-list"
            WebElement cartList = driver.findElement(By.className("cart_list"));

            // Temukan semua elemen dalam "cart-list" dengan class "cart-item"
            List<WebElement> cartItems = cartList.findElements(By.className("cart_item"));

            // Verifikasi bahwa jumlah item dalam keranjang sesuai dengan yang diharapkan
            int expectedItemCount = this.totalItemsAdded; // Gunakan nilai yang telah dihitung sebelumnya
            Assert.assertEquals(expectedItemCount,cartItems.size());

        }
    }
}
