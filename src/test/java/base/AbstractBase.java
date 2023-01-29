package base;

import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

abstract public class AbstractBase {

    @SneakyThrows
    protected static void sleep(double second) {
        Thread.sleep((long) (second * 1000L));
    }

    @SneakyThrows
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] captureScreen(WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("./target/allure-results/screenshots//" + screenshot.getName()));
        return Files.toByteArray(screenshot);
    }
}
