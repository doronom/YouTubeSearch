package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
public class CustomerLoginPage {
    @FindBy(how = How.CSS, using = "[class=\"form-group\"]")
    public static WebElement form;

    @FindBy(how = How.ID, using = "userSelect")
    public static WebElement usersSelector;

    @FindBy(how = How.CSS, using = "[value=\"2\"]")
    public static WebElement harryPotter;

    @FindBy(how = How.CSS, using = "[ng-show=\"custId != ''\"]")
    public static WebElement loginButton;
}
