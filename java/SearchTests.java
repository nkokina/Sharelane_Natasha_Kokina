import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests {
    private WebDriver driver;


    @BeforeClass
    public void driver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openSharelane() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        driver.findElement(By.name("first_name")).sendKeys("za");
        driver.findElement(By.name("last_name")).sendKeys("za");
        driver.findElement(By.name("email")).sendKeys("za@google.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
    }

    @AfterClass
    public void tearDown() {

        driver.quit();
    }

    @Test
    public void whenTheNameOfTheBookWasEnteredIntoTheSearchThenTheBookOpened() {
        driver.findElement(By.name("keyword")).sendKeys("The Moon and Sixpence");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Search']"));
        registerButton.click();
        Assert.assertEquals(driver.getPageSource().contains("add_to_cart"), true,
                "The requested book was not found.");
    }

    @Test
    public void whenWeSearchForTheAuthorInTheSearchThenTheBookOpens() {
        driver.findElement(By.name("keyword")).sendKeys("W.Somerset Maugham");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Search']"));
        registerButton.click();
        Assert.assertEquals(driver.getPageSource().contains("add_to_cart"), true,
                "Book search by author did not work.");
    }
}