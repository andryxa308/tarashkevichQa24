import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Email {
    private static final String URL="https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345";
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @BeforeMethod
    public void navigate() {
        driver.get(URL);
    }
    @Test
    public void positiveEmailTest() {
        WebElement EmailInput = driver.findElement(By.cssSelector("input[name=email]"));
        EmailInput.clear();
        EmailInput.sendKeys("amit_lin@231.57.sharelane.com");
        WebElement RegisterButton = driver.findElement(By.cssSelector("input[value=Register]"));
        RegisterButton.click();
        EmailInput = driver.findElement(By.cssSelector("input[name=email]"));
        Assert.assertTrue(EmailInput.isDisplayed());
    }
    @Test
    public void negativeEmailTest() {
        WebElement EmailInput = driver.findElement(By.cssSelector("input[name=email]"));
        EmailInput.clear();
        EmailInput.sendKeys("11");
        WebElement RegisterButton = driver.findElement(By.cssSelector("input[value=Register]"));
        RegisterButton.click();
        EmailInput = driver.findElement(By.cssSelector("input[name=email]"));
        Assert.assertTrue(EmailInput.isDisplayed());
        WebElement errorMessage = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(errorMessage.isDisplayed());
        String expectedErrorMessageText = "Oops, error on page. Some of your fields have invalid data or email was previously used";
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessageText);

    }



}
