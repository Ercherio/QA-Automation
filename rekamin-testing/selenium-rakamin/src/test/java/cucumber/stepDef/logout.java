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

import java.io.StreamTokenizer;
import java.util.concurrent.TimeUnit;

public class logout extends env {

    @Given("user is on saucedemo login page for logout")
    public void user_is_on_saucedemo_login_page_for_logout(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("user input (.*) as user_name for logout$")
    public void userInputUser_nameAsUser_nameForLogout(String user_name) {
        driver.findElement(By.id("user-name")).sendKeys(user_name);
    }

    @And("user input (.*) as password for logout$")
    public void userInputPasswordAsPasswordForLogout(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("user click submit for logout")
    public void userClickSubmitForLogout() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("user login result for logout")
    public void userLoginResultForLogout() {
        String dashboardPageAssert = driver.findElement(By.className("title")).getText();
        Assert.assertEquals("Products",dashboardPageAssert );
    }

    @Then("user show sidebar")
    public void userShowSidebar() {
        driver.findElement(By.className("bm-burger-button")).click();
    }


    @Then("user click button logout")
    public void userClickButtonLogout() {
        // Temukan elemen <nav> dengan kelas "bm-item-list"
        WebElement navElement = driver.findElement(By.xpath("//nav[@class='bm-item-list']"));

        // Di dalam elemen <nav>, temukan elemen <a> dengan id "logout_sidebar_link"
        WebElement logoutLink = navElement.findElement(By.xpath(".//a[@id='logout_sidebar_link']"));

        // Klik elemen logoutLink
        logoutLink.click();

    }

    @Then("user verify (.*) logout$")
    public void userVerifyStatusLogout(String status) {
        if (status.equals("success")) {
            String loginPageAssert = driver.findElement(By.className("login_logo")).getText();

            Assert.assertEquals("Swag Labs",loginPageAssert);
        }
    }

}
