package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import config.env;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class login extends env {

    @Given("user is on saucedemo login page")
    public void user_is_on_saucedemo_login_page() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("user input (.*) as user_name$")
    public void user_input_standar_user_as_user_name(String user_name) {
        driver.findElement(By.id("user-name")).sendKeys(user_name);
    }
    @And("user input (.*) as password$")
    public void user_input_secret_sauce_as_password(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("user click submit")
    public void user_click_submit() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("user verify (.*) login result$")
    public void user_verify_success_login_result(String status) {
        if (status.equals("success")) {
            String dashboardPageAssert = driver.findElement(By.className("title")).getText();
            Assert.assertEquals("Products",dashboardPageAssert);
        } else {
            String errorLoginAssert = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
            Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",errorLoginAssert);
        }
    }
}
