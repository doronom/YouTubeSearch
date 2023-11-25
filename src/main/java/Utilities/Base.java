package Utilities;

import PageObjects.YouTubeSelectedVideoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

//***** This class store all the global variables of the project *****
public class Base {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions action;
    public static Screenshot imageScreenshot;
    public static ImageDiffer imgDif = new ImageDiffer();
    public static ImageDiff dif;

    public static String Platform;
    public static PageObjects.YouTubeMainPage youTubeMainPage;
    public static PageObjects.YouTubeSearchResultsPage searchResultsPage;
    public static PageObjects.YouTubeSelectedVideoPage youTubeSelectedVideoPage;

}
