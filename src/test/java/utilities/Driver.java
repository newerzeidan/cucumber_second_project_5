package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver;

    private Driver() {};

    public static WebDriver getDriver() {
        String browser = ConfigReader.getProperty("browser");

        if (driver == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    WebDriverManager.getInstance(EdgeDriver.class).setup();
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalStateException(browser + " is not available!");
            }

            driver.manage().window().fullscreen();
            driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigReader.getProperty("implicitWait")), TimeUnit.SECONDS);
        }

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }
    }
}