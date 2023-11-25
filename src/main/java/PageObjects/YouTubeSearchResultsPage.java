package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class YouTubeSearchResultsPage {
    @FindBy(how = How.CSS, using = "#filter-button > ytd-button-renderer > yt-button-shape\n")
    public static WebElement filterButton;

    @FindBy(how = How.CSS, using = "button[id=\"button\"][aria-label=\"Cancel\"]")
    public static WebElement closeFilterButton;

    @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'external-icon')])[1]")
    public static WebElement youTubeLogo;

    @FindBy(how = How.XPATH, using = "//*[@id='endpoint']//*[@class='style-scope ytd-search-filter-renderer' and contains(text(), 'Video')]")
    public static WebElement videoFilterType;

    @FindBy(how = How.XPATH, using = "//yt-formatted-string[@class='style-scope ytd-search-filter-renderer' and contains(text(), 'View count')]\n")
    public static WebElement sortByViewCount;

    @FindBy(how = How.CSS, using = "div[id=\"contents\"]")
    public static List<WebElement> videoList;

    @FindBy(how = How.XPATH, using = "//span[@class='inline-metadata-item style-scope ytd-video-meta-block' and contains(text(), 'views')]\n")
    public static List<WebElement> viewCountList;
    @FindBy(how = How.CSS, using = "a[id=\"thumbnail\"][href*='watch?v=ybXrrTX3LuI']")
    public static WebElement videoThumbnail;

    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'style-scope ytd-channel-name complex-string')]//a[contains(@class, 'yt-simple-endpoint style-scope') and @href=\"/@nikki7993\"]")
    public static WebElement postedUserChannel;

}
