package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshoter {

    public static void makeScreen() {
        WebDriver driver = WebDriverCreator.getDriver();

        String screenshotName = String.valueOf(System.nanoTime());
        String fileName = screenshotName + ".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshot, new File("C:\\DeviceScreenshots\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create screenshot.");
        }

    }


}
