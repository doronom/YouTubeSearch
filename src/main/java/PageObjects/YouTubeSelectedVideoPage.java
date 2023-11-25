package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class YouTubeSelectedVideoPage {
    @FindBy(how = How.CSS, using = "tp-yt-paper-button[id=\"expand\"]")
    public static WebElement moreButton;

    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'ytp-ad-skip-button-modern')]//div[contains(@id, 'ad-text')]")
    public static WebElement skipVideoButton;

    @FindBy(how = How.XPATH, using = "//span[@class='ytp-ad-preview-image-modern']/img[@class='ytp-ad-image']")
    public static WebElement videoPreAdsSkipButton;

    @FindBy(how = How.XPATH, using = "//video[contains(@class, \"video-stream\")]")
    public static WebElement videoWindowDisplay;

    @FindBy(how = How.CSS, using = "h4[class*=\"yt-video-attribute-view-model\"]")
    public static WebElement artistName;

}
