package Utilities;

import org.openqa.selenium.support.PageFactory;

//This class is the management of the page objects
public class ManagePages extends Base {

    public static void init() {
        youTubeMainPage = PageFactory.initElements(driver, PageObjects.YouTubeMainPage.class);
        searchResultsPage = PageFactory.initElements(driver, PageObjects.YouTubeSearchResultsPage.class);
        youTubeSelectedVideoPage = PageFactory.initElements(driver, PageObjects.YouTubeSelectedVideoPage.class);
    }
}
