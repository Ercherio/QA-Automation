package cucumber.stepDef;

import config.env;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class resetappstate extends env{
    @Given("user is on saucedemo login page for ras")
    public void user_is_on_saucedemo_login_page_for_ras(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("user input (.*) as user_name for ras$")
    public void userInputUser_nameAsUser_nameForRas(String user_name) {
        driver.findElement(By.id("user-name")).sendKeys(user_name);
    }

    @And("user input (.*) as password for ras$")
    public void userInputPasswordAsPasswordForRas(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("user click submit for ras")
    public void userClickSubmitForRas() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("user login result for ras")
    public void userLoginResultForRas() {
        String dashboardPageAssert = driver.findElement(By.className("title")).getText();
        Assert.assertEquals("Products", dashboardPageAssert);
    }

    @And("user click Add to cart on some product")
    public void userClickAddToCartOnSomeProduct() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        totalItemsAdded++;

        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        totalItemsAdded++;

        String addToCartAssert = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(totalItemsAdded,Integer.parseInt(addToCartAssert));
    }

    @Then("user show sidebar ras")
    public void userShowSidebarRas() {
        driver.findElement(By.className("bm-burger-button")).click();
    }

    @Then("user click button ras")
    public void userClickButtonRas() {
        // Temukan elemen <nav> dengan kelas "bm-item-list"
        WebElement navElement = driver.findElement(By.xpath("//nav[@class='bm-item-list']"));


        // Di dalam elemen <nav>, temukan elemen <a> dengan id "logout_sidebar_link"
        WebElement resetLink = navElement.findElement(By.xpath(".//a[@id='reset_sidebar_link']"));

        // Klik elemen logoutLink
        resetLink.click();
    }

    @Then("user verify (.*) ras$")
    public void userVerifyStatusRas(String status) {
        String addToCartAssert = "";

        if (status.equals("success")){
            try{
                addToCartAssert = driver.findElement(By.className("shopping_cart_badge")).getText();
                Assert.assertEquals(addToCartAssert,"");

            } catch (NoSuchElementException e){

                String sauceLabsBackpack = driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
                String sauceLabsBikeLight = driver.findElement(By.id("remove-sauce-labs-bike-light")).getText();

                Assert.assertEquals("",sauceLabsBackpack);
                Assert.assertEquals("",sauceLabsBikeLight);

                Assert.assertEquals("",addToCartAssert);
            }
        }

    }
}
