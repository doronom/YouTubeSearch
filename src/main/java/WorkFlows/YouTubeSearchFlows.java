package WorkFlows;

import Extensions.FileLogger;
import Extensions.ScenarioContext;
import Extensions.ScenarioContext.*;
import Extensions.UiActions;
import PageObjects.YouTubeMainPage;
import PageObjects.YouTubeSearchResultsPage;
import PageObjects.YouTubeSelectedVideoPage;
import Utilities.CommonOperations;
import Utilities.HelpersMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import java.util.List;

public class YouTubeSearchFlows extends CommonOperations {

    @Step("Navigate to YouTube main page and search for a given phrase")
    public static void navigateToYouTubeAndSearchPhrase(String searchPhrase) {
        FileLogger.info("Browse to YouTube and search for the given phrase");
        UiActions.waitUntilElementIsClickable(YouTubeMainPage.searchInput, 5);
        UiActions.fillInputField(YouTubeMainPage.searchInput, searchPhrase);
        UiActions.sendKeyboardKey(YouTubeMainPage.searchInput, Keys.ENTER);
        UiActions.waitUntilElementIsVisible(YouTubeSearchResultsPage.filterButton, 2);
    }

    @Step("Filter results by given category")
    public static void filterResultsByUserSelection(WebElement element) {
        FileLogger.info("Filter the results by user selection");
        UiActions.waitUntilElementIsClickable(YouTubeSearchResultsPage.filterButton, 5);
        UiActions.click(YouTubeSearchResultsPage.filterButton);
        UiActions.waitUntilElementIsClickable(element, 5);
        UiActions.click(element);
    }

    @Step("Save results to context")
    public static void saveResultsListToContext(List<Double> list, Key<List<Double>> key) {
        UiActions.waitUntilAllElementsAreVisible(YouTubeSearchResultsPage.viewCountList, 10);
        list = HelpersMethods.saveViewCountToList(YouTubeSearchResultsPage.viewCountList);
        ScenarioContext.setInDataStore(key, list);
    }

    @Step("Retrieve user/channel name who posted a video")
    public static void getPostedUserChannel() {
        UiActions.waitUntilElementIsVisible(YouTubeSearchResultsPage.videoThumbnail, 10);
        String postedUser = YouTubeSearchResultsPage.postedUserChannel.getAttribute("innerText");
        FileLogger.info("*************************** The user/channel name who posted that video is: " + postedUser + " ***************************");
    }


    @Step("Play the video and click on skip ads button in case it's displayed")
    public static void checkIfSkipNeededAndPlayVideo() {
        UiActions.click(YouTubeSearchResultsPage.videoThumbnail);
        try {
            // Check if the video pre-ads skip button is displayed
            if (YouTubeSelectedVideoPage.videoPreAdsSkipButton.isEnabled()) {
                UiActions.waitUntilElementIsClickable(YouTubeSelectedVideoPage.skipVideoButton, 10);
                FileLogger.info("Waiting for skip button to be available");

                // If displayed, click on the skip button
                UiActions.click(YouTubeSelectedVideoPage.skipVideoButton);

                // Wait for the video window to be clickable
                UiActions.waitUntilElementIsClickable(YouTubeSelectedVideoPage.videoWindowDisplay, 5);

                // Let the video play for 30 seconds
                UiActions.pauseVideoAfterSeconds(YouTubeSearchResultsPage.videoThumbnail,30);
            }
        } catch (NoSuchElementException e) {
            // Handle the exception (e.g., log the message or perform alternative actions)
            FileLogger.info("Video pre-ads skip button not found. Proceeding without skipping.");
            UiActions.pauseVideoAfterSeconds(YouTubeSearchResultsPage.videoThumbnail,30);
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions if needed
        }
    }


    @Step("Retrieve the artist name and print it")
    public static void getArtisName() {
        UiActions.click(YouTubeSelectedVideoPage.moreButton);
        UiActions.waitUntilElementIsVisible(YouTubeSelectedVideoPage.artistName, 5);
        FileLogger.info("*************************** The artist name is: " + YouTubeSelectedVideoPage.artistName.getAttribute("innerText") + " ***************************");
    }
}
