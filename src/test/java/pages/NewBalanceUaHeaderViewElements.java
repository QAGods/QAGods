package pages;

import base.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewBalanceUaHeaderViewElements extends AbstractBasePage {
    public NewBalanceUaHeaderViewElements(WebDriver driver) {
        super(driver);
    }
    private static final String MAN_CATEGORY_BTN = "//div[@class='nav']//a[@href='/store/man']";
    private static final String MAN_CATEGORY_SHOES_BTN = MAN_CATEGORY_BTN + "/..//a[@class='nav__heading'][contains(@href,'vzutta')]";

    public WebElement manCategoryBtn() {
        return waitUntilElementToBeClickable(MAN_CATEGORY_BTN);
    }
    public WebElement manCategoryShoesBtn() {
        return waitUntilElementToBeClickable(MAN_CATEGORY_SHOES_BTN);
    }
}
