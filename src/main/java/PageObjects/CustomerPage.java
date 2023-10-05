package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CustomerPage {
    @FindBy(how = How.CSS, using = "span[class$='ng-binding']")
    public static WebElement welcomeTitle;

    @FindBy(how = How.CSS, using = "[ng-click=\"transactions()\"]")
    public static WebElement transactionsButton;

    @FindBy(how = How.CSS, using = "[ng-click=\"back()\"]")
    public static WebElement backButton;

    @FindBy(how = How.CSS, using = "[ng-click=\"deposit()\"]")
    public static WebElement depositButton;

    @FindBy(how = How.CSS, using = "button[class$=\"btn-default\"]")
    public static WebElement transactionSubmitButton;

    @FindBy(how = How.CSS, using = "span[ng-show=\"message\"]")
    public static WebElement transactionMessage;

    @FindBy(how = How.CSS, using = "div.center strong.ng-binding:nth-of-type(2)")
    public static WebElement accountBalance;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),\"Amount to be Withdrawn\")]")
    public static WebElement amountInputLabel;
    @FindBy(how = How.CSS, using = "[ng-click=\"withdrawl()\"]")
    public static WebElement withdrawButton;

    @FindBy(how = How.CSS, using = "[ng-model=\"amount\"]")
    public static WebElement amountInputField;
}
