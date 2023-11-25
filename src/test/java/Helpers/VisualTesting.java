package Helpers;

import PageObjects.YouTubeSearchResultsPage;
import Utilities.CommonOperations;
import Utilities.HelpersMethods;
import WorkFlows.YouTubeSearchFlows;
import org.testng.annotations.Test;

//This class demonstrate an automation visual testing

public class VisualTesting extends CommonOperations
{
    @Test
    public void takeScreenShot()
    {
        YouTubeSearchFlows.navigateToYouTubeAndSearchPhrase(getData("searchPhrase"));
        HelpersMethods.takeElementScreenShot(YouTubeSearchResultsPage.youTubeLogo, "youTubeLogo");
    }
}
