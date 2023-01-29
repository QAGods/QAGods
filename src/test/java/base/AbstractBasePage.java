package base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.fail;

@Slf4j
abstract public class AbstractBasePage extends AbstractBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        this.actions = new Actions(driver);
    }

    protected WebElement waitUntilVisibilityOfElementLocated(String locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        } catch (WebDriverException e) {
            log.error("No visibility element: " + locator);
            fail("No visibility element: " + locator);
            return null;
        }
    }

    protected WebElement waitUntilElementToBeClickable(String locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        } catch (WebDriverException e) {
            log.error("No clickable element: " + locator);
            fail("No clickable element: " + locator);
            return null;
        }
    }

    protected List<WebElement> waitUntilVisibilityOfElementsLocated(String locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            return driver.findElements(By.xpath(locator));
        } catch (WebDriverException e) {
            log.error("No visibility elements: " + locator);
            fail("No visibility elements: " + locator);
            return null;
        }
    }

    protected List<WebElement> waitUntilElementsToBeClickable(String locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            return driver.findElements(By.xpath(locator));
        } catch (WebDriverException e) {
            log.error("No clickable elements: " + locator);
            fail("No clickable elements: " + locator);
            return null;
        }
    }

    public void waitUntilUrlContainsText(String urlPath) {
        try {
            wait.until(ExpectedConditions.urlContains(urlPath));
        } catch (WebDriverException e) {
            log.error("This URL path is missing: " + urlPath);
            fail("This URL path is missing: " + urlPath);
            throw new AssertionError("This URL path is missing: " + urlPath);
        }
    }

    public void waitUntilNumberOfTabToBe(int tabNumber) {
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(tabNumber));
        } catch (WebDriverException e) {
            log.error("No tabs with this number: " + tabNumber);
            fail("No tabs with this number: " + tabNumber);
            throw new AssertionError("No tabs with this number: " + tabNumber);
        }
    }

    public void goToNextTab(int tabNumber) {
        waitUntilNumberOfTabToBe(tabNumber);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNumber - 1));
    }

    protected void moveCursor(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    protected void doubleClick(WebElement element) {
        actions.doubleClick(element).build().perform();
    }
}
