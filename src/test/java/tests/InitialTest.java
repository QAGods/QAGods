package tests;

import base.AbstractBaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.NewBalanceUaHeaderViewHelper;

public class InitialTest extends AbstractBaseTest {

    @Owner("SafroN")
    @Epic(value = "Header view")
    @Test(groups = "Smoke")
    @Description("This is test's description")
    @Severity(SeverityLevel.MINOR)
    public void manShoesTest() {
        NewBalanceUaHeaderViewHelper newBalanceUaHeaderView = new NewBalanceUaHeaderViewHelper(driver);

        openUrl("https://newbalance.ua/");
        newBalanceUaHeaderView.goToMansShoesPage();

        Assertions.assertThat(getUrl()).isEqualTo("https://newbalance.ua/store/man/vzutta");
    }
}
