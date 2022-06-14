import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    private WebDriver driver;


    @BeforeAll
    public static void Path() {
        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldSetForm() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Никита-Алексндрович");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79012345678");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button__content")).click();
        String actualText = driver.findElement(By.cssSelector(".Success_successBlock__2L3Cw")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";

        assertEquals(expected, actualText);
    }

    @Test
    public void validationOfTheNameField() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Nikita");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79012345678");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button__content")).click();
        String actualText = driver.findElement(By.cssSelector(".input__sub")).getText().trim();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";

        assertEquals(expected, actualText);

    }

    @Test
    public void validationOfThePhoneField() {

        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Никита");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("79012345678");
        List<WebElement> textFields = driver.findElements(By.cssSelector(".input__sub"));
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button__content")).click();
        String actualText = textFields.get(1).getText().trim();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";

        assertEquals(expected, actualText);

    }
}
