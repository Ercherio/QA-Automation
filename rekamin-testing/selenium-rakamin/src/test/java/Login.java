import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import config.env;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login extends env{

    @Test //Tag untuk running script dibawah ini
    public void login_succes(){
        WebDriver driver;
        //String baseUrl = "https://www.saucedemo.com/";

        //WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup(); // Set up FirefoxDriver

        //Apply chrome driver setup
        //driver= WebDriverManager.chromedriver().create();
        //driver= new ChromeDriver();

        driver  = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().maximize(); // maximize windows

       // String loginPageAssert = driver.findElement(By.xpath("//h2[contains(text(),'Swag Labs')]")).getText();
        String loginPageAssert = driver.findElement(By.className("login_logo")).getText();

        Assert.assertEquals(loginPageAssert,"Swag Labs");

        //input email
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //input password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //click login
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //asert nama toko
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("app_logo")));
        String namaToko = driver.findElement(By.className("app_logo")).getText();
        Assert.assertEquals(namaToko, "Swag Labs");
        //quit
        driver.close();
    }

    @Test //Tag untuk running script dibawah ini
    public void login_failed(){
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup(); // Set up FirefoxDriver

        driver  = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().maximize(); // maximize windows
        // String loginPageAssert = driver.findElement(By.xpath("//h2[contains(text(),'Swag Labs')]")).getText();
        String loginPageAssert = driver.findElement(By.className("login_logo")).getText();

        Assert.assertEquals(loginPageAssert,"Swag Labs");

        //input email
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //input password
        driver.findElement(By.id("password")).sendKeys("secret_sauc");
        //click login
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //asert button error
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(),'Epic sadface: Username and password do not match any user in this service')]")));

        String errprLogin = errorElement.getText();
        Assert.assertEquals(errprLogin, "Epic sadface: Username and password do not match any user in this service");
        //quit
        driver.close();

    }


}
