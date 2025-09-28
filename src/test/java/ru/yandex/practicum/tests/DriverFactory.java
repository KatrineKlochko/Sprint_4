package ru.yandex.practicum.tests;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.MainPage;

import java.time.Duration;

public class DriverFactory extends ExternalResource {

    private WebDriver driver;
    private WebDriverWait wait;

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    @Override
    protected void before() {
        initDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Клик по куки
        try {
            WebElement cookie = wait.until(ExpectedConditions.elementToBeClickable(MainPage.cookieButton));
            cookie.click();
        } catch (Exception ignored) {}
    }

    @Override
    protected void after() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void initDriver(){

        if("firefox".equals(System.getProperty("browser"))){
            StartFirefox();
        } else {
            StartChrome();
        }
    }

    private void StartChrome() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    private void StartFirefox() {

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

}
