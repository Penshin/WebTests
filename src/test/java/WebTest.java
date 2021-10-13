import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WebTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/stanislav/Downloads/chromedriver");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void setDown(){
        driver.quit();
    }

    @Test
    public void headerSearchTest() throws InterruptedException {
        driver.get("https://www.homedepot.com");

        WebElement searchField = driver.findElement(By.id("headerSearch"));
        searchField.sendKeys("tube clamp");
        WebElement btn = driver.findElement(By.xpath("//button[@id='headerSearchButton']"));
        btn.click();

        WebElement header = driver.findElement(By.xpath("//h1[@class='results-header__keyword']"));
        Assert.assertEquals(header.getText(), "Tube Clamp");
    }

    @Test
    public void redirectToSignInTest() throws InterruptedException {
        driver.get("https://www.homedepot.com");

        WebElement accountIcon = driver.findElement(By.xpath("//a[@id='headerMyAccount']"));
        accountIcon.click();

        WebElement signInIcon = driver.findElement(By.xpath("//span[text()=' Sign in ']"));
        signInIcon.click();

        Assert.assertEquals(driver.getTitle(), "The Home Depot: sign in, create or secure your account");
    }
}

