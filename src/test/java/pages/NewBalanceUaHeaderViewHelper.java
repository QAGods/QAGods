package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class NewBalanceUaHeaderViewHelper extends NewBalanceUaHeaderViewElements {
    public NewBalanceUaHeaderViewHelper(WebDriver driver) {
        super(driver);
    }

    @Step("Go to page with man shoes by move cursor on MAN category and click on shoes")
    public void goToMansShoesPage() {
        moveCursor(manCategoryBtn());
        manCategoryShoesBtn().click();
    }
}
