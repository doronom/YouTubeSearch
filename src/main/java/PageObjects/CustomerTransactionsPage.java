package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CustomerTransactionsPage {
    @FindBy(how = How.CSS, using = "[class=\"table table-bordered table-striped\"]")
    public static WebElement table;

    @FindBy(how = How.CSS, using = "#anchor0,#anchor1")
    public static List<WebElement> tableRows;

    @FindBy(how = How.ID, using = "anchor0")
    public static List<WebElement> tableFirstRow;

    @FindBy(how = How.ID, using = "anchor1")
    public static List<WebElement> tableSecondRow;

    @FindBy(how = How.XPATH, using = "(//tr[contains(@id, \"anchor0\")]//td[contains(@class, \"ng-binding\")])[2]")
    public static WebElement firstRowAmountColumn;
    @FindBy(how = How.XPATH, using = "(//tr[contains(@id, \"anchor0\")]//td[contains(@class, \"ng-binding\")])[3]")
    public static WebElement firstRowTransactionTypeColumn;

    @FindBy(how = How.XPATH, using = "(//tr[contains(@id, \"anchor1\")]//td[contains(@class, \"ng-binding\")])[2]")
    public static WebElement secondRowAmountColumn;
    @FindBy(how = How.XPATH, using = "(//tr[contains(@id, \"anchor1\")]//td[contains(@class, \"ng-binding\")])[3]")
    public static WebElement secondRowTransactionTypeColumn;

    @FindBy(how = How.TAG_NAME, using = "td")
    public static List<WebElement> tableColumns;
}
