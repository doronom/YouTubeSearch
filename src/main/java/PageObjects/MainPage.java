package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//This class holds all the locators of Google main page that we are using in the project
public class MainPage {
    @FindBy(how = How.CSS, using = "[ng-click=\"customer()\"]")
    public static WebElement customerLogin;

}
