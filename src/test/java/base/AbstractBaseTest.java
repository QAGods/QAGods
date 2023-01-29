package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;

@Slf4j
abstract public class AbstractBaseTest extends AbstractBase{
    protected static WebDriver driver;
    private final Dimension dimension = new Dimension(WIDTH, HEIGHT);
    private static final int WIDTH = 1680;
    private static final int HEIGHT = 1050;

    /**
     * Requirement:
     * docker run --name=chrome --rm --network=host -p 4444:4444 -p 7900:7900 --shm-size="2g" -e SE_NODE_MAX_SESSIONS=8 -e SE_NODE_OVERRIDE_MAX_SESSIONS=true -e SE_NODE_SESSION_TIMEOUT=90 -e SE_SCREEN_WIDTH=1920 -e SE_SCREEN_HEIGHT=1080 -e SE_SCREEN_DEPTH=24 -e SE_SCREEN_DPI=74 selenium/standalone-chrome
     * */
    private static final String REMOTE_WEBDRIVER_HOST = "http://localhost:4444";

    @SneakyThrows
    @BeforeMethod
    public void openBrowser() {
        log.info("===>>> Setup driver");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        if ("false".equals(System.getProperty("runRemote", "true"))) {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(REMOTE_WEBDRIVER_HOST), options);
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            driver = remoteWebDriver;
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        driver.manage().window().setSize(dimension);
    }

    @AfterMethod
    public void closeBrowser() {
        log.info("<<<=== Teardown");
        driver.quit();
    }

    public void openUrl(String url) {
        driver.navigate().to(url);
    }
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    protected boolean urlContains(String urlPath) {
        return driver.getCurrentUrl().contains(urlPath);
    }

    public void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
