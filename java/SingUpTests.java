import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SingUpTests {

    private WebDriver driver;


    @BeforeClass
    public void driver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void openSharelane() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
    }

    @AfterClass
    public void tearDown() {

        driver.quit();
    }

    @Test
    public static void whenZipCodeEnterCorrectThanFirstNameDisplayed() {

        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed(),
                "Zip code should not be displayed");
        Assert.assertTrue(driver.findElement(By.name("first_name")).isDisplayed(),
                "First Name input should be displayed");
    }

    @Test
    public void whenZipCodeEnterNotCorrectThanErrorMessageDisplayed() {
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("7788954A");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        WebElement errorMassage = driver.findElement(By.className("error_message"));
        Assert.assertTrue(errorMassage.isDisplayed(), "An error message not appeared");
        Assert.assertTrue(errorMassage.getAttribute("innerHTML")
                        .equals("Oops, error on page. ZIP code should have 5 digits"),
                "An error message not correct");

        Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(),
                "Zip code should be displayed");

    }

    @Test
    public void whenZipCodeNotEnterThanErrorMessageDisplayed()  {
        driver.findElement(By.name("zip_code")).sendKeys("");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        WebElement errorMassage = driver.findElement(By.className("error_message"));
        Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(), "Zip code should be displayed");
        Assert.assertTrue(errorMassage.isDisplayed(), "An error message not appeared");
    }

    @Test
    public void whenEmptyFieldsInTheAuthorizationThenTheInscriptionAppearsTests() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        driver.findElement(By.name("first_name")).sendKeys("");
        driver.findElement(By.name("last_name")).sendKeys("");
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.name("password1")).sendKeys("");
        driver.findElement(By.name("password2")).sendKeys("");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Does not display a message about invalid registration.");
    }

    @Test
    public void whenEnterCorrectDataInSingUpThanSuccessMessageDisplayTests()  {
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
        WebElement successMessage = driver.findElement(By.className("confirmation_message"));
        Assert.assertTrue(successMessage.isDisplayed(),
                "Error in registration, does not display a message about successful registration.");
    }

    @Test
    public void whenYouEnteredNumbersInTheNameDuringRegistrationThenAnErrorMessageIsDisplayedTests() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        driver.findElement(By.name("first_name")).sendKeys("12");
        driver.findElement(By.name("last_name")).sendKeys("12");
        driver.findElement(By.name("email")).sendKeys("za@google.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        Assert.assertTrue(errorMessage.isDisplayed(),
                "Does not display a message about invalid registration.");

    }
}
