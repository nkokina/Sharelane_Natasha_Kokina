import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SingUpTests {

    @Test
    public static void whenZipCodeEnterCorrectThanFirstNameDisplayed() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed(),
                "Zip code should not be displayed");
        Assert.assertTrue(driver.findElement(By.name("first_name")).isDisplayed(),
                "First Name input should be displayed");
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    public void whenZipCodeEnterNotCorrectThanErrorMessageDisplayed() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
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
        Thread.sleep(1000);
        driver.quit();


    }

    @Test
    public void whenZipCodeNotEnterThanErrorMessageDisplayed() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        WebElement errorMassage = driver.findElement(By.className("error_message"));
        Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(), "Zip code should be displayed");
        Assert.assertTrue(errorMassage.isDisplayed(), "An error message not appeared");
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    public void whenEmptyFieldsInTheAuthorizationThenTheInscriptionAppearsTests() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
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
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    public void whenEnterCorrectDataInSingUpThanSuccessMessageDisplayTests() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
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
        WebElement successMessage = driver.findElement(By.className("confirmation_message"));
        Assert.assertTrue(successMessage.isDisplayed(),
                "Error in registration, does not display a message about successful registration.");
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    public void whenYouEnteredNumbersInTheNameDuringRegistrationThenAnErrorMessageIsDisplayedTests() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
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
        Thread.sleep(1000);
        driver.quit();
    }
}
