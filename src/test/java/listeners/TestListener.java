package listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static base.AbstractBase.captureScreen;
import static base.AbstractBaseTest.getDriver;

@Slf4j
public class TestListener implements TestLifecycleListener {
    private WebDriver driver;

    @Override
    public void beforeTestStart(TestResult result) {
        log.info("===>>> Test started: " + result.getName());
    }

    @Override
    public void beforeTestStop(TestResult result) {
        driver = getDriver();
        switch (result.getStatus()) {
            case PASSED:
                log.info("<<<=== Test completed successfully: " + result.getName());
                if (driver != null)
                    captureScreen(driver);
                break;
            case FAILED:
                log.error("<<<=== Test field: " + result.getName());
                if (driver != null)
                    captureScreen(driver);
                break;
            case SKIPPED:
                log.error("<<<=== Test skipped: " + result.getName());
                if (driver != null)
                    captureScreen(driver);
                break;
        }
    }
}
