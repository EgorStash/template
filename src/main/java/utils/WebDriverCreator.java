package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class WebDriverCreator {

    private static WebDriver driver;

    private WebDriverCreator() {
    }

    public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        return init();
    }

    private static WebDriver init() {
        System.setProperty("webdriver.chrome.driver", String.valueOf(Paths.get("drivers", "chromedriver.exe")));
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void kill() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Browser imortal!");
            } finally {
                driver = null;
            }
        }
    }
}
