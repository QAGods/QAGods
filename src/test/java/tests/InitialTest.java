package tests;

import base.AbstractBaseTest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.NewBalanceUaHeaderViewHelper;

public class InitialTest extends AbstractBaseTest {

    @Test
    public void manShoesTest() {
        NewBalanceUaHeaderViewHelper newBalanceUaHeaderView = new NewBalanceUaHeaderViewHelper(driver);

        openUrl("https://newbalance.ua/");
        newBalanceUaHeaderView.goToMansShoesPage();

        Assertions.assertThat(getUrl()).isEqualTo("https://newbalance.ua/store/man/vzutta");
    }
}
