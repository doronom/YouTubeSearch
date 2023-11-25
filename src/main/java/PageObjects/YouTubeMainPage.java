package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.By;

//This class holds all the locators of YouTube main page that we are using in the project
public class YouTubeMainPage {
    @FindBy(how = How.CSS, using = "input[id=\"search\"]")
    public static WebElement searchInput;

    @FindBy(how = How.CSS, using = "button[id=\"search-icon-legacy\"]")
    public static WebElement searchButton;

}
