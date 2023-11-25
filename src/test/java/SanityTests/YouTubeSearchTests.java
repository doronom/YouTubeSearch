package SanityTests;

import Extensions.FileLogger;
import Extensions.ScenarioContext;
import Extensions.UiActions;
import Extensions.Verifications;
import PageObjects.YouTubeSearchResultsPage;
import PageObjects.YouTubeSelectedVideoPage;
import Utilities.CommonOperations;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import WorkFlows.YouTubeSearchFlows;

import java.util.ArrayList;
import java.util.List;

import static Extensions.Verifications.visualElement;

@Listeners(Utilities.Listeners.class)
public class YouTubeSearchTests extends CommonOperations {

    private List<Double> countViewsBeforeSorting = new ArrayList<>();
    private List<Double> countViewsAfterSorting = new ArrayList<>();
    @Test(description = "Test 01: search for a given phrase")
    @Description("Test Description: navigate to Youtube and search for a given phrase")
    public void test01_searchForPhrase() {
        FileLogger.info("Starting test01_searchForPhrase");
        //Browse to YouTube and search for the given phrase
        YouTubeSearchFlows.navigateToYouTubeAndSearchPhrase(getData("searchPhrase"));

        //Validate that the results page title display the search phrase
        Verifications.text(UiActions.getPageTitle(), getData("expectedResultsPageTitle"));
        //Visual testing on YouTube logo
        Verifications.visualElement(YouTubeSearchResultsPage.youTubeLogo, "youTubeLogo");

        //Filter the results list by video type
        YouTubeSearchFlows.filterResultsByUserSelection(YouTubeSearchResultsPage.videoFilterType);

        //Save the count views as unsorted list before sorting by view count from filters menu
        YouTubeSearchFlows.saveResultsListToContext(countViewsBeforeSorting, ScenarioContext.countViewsBeforeSorting);
        YouTubeSearchFlows.filterResultsByUserSelection(YouTubeSearchResultsPage.sortByViewCount);

        //Save the count views as sorted list after sorting by view count from filters menu
        YouTubeSearchFlows.saveResultsListToContext(countViewsAfterSorting, ScenarioContext.countViewsAfterSorting);

        //Validating that the count views after sorting displayed in descending order
        Verifications.validateDescendingOrder();

        //Find the video from the list and print the user/channel name who posted the video
        YouTubeSearchFlows.getPostedUserChannel();

        //Play the video and check if there is a need to skip an ad before playing the video
        YouTubeSearchFlows.checkIfSkipNeededAndPlayVideo();

        //Fetch the artist's name and print it
        YouTubeSearchFlows.getArtisName();

        //Validate the expected artist name
        Verifications.text(YouTubeSelectedVideoPage.artistName.getAttribute("innerText"), getData("expectedArtistName"));
        FileLogger.info("Finishing test01_searchForPhrase");
    }

}
