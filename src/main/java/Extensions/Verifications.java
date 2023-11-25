package Extensions;

import Utilities.CommonOperations;
import Utilities.HelpersMethods;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class Verifications extends CommonOperations {

    private static int RETRY_INTERVAL_IN_SECONDS = 10;
    @Step("Verify text in element")
    public static void validateTextInElement(WebElement element, String expectedValue) {
        Assert.assertEquals(element.getText(), expectedValue);
    }

    //This method read the previous version image logo and compare it to the new version image logo
    @Step("Compare main image logo")
    public static void visualElement(WebElement imageElement, String expectedImageName) {
        try {
            BufferedImage expectedImage = ImageIO.read(new File(getData("ImageRepo") + expectedImageName + ".png"));

            Screenshot imageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, imageElement);
            BufferedImage actualImage = imageScreenshot.getImage();

            dif = imgDif.makeDiff(actualImage, expectedImage);
            assertFalse(dif.hasDiff(), "Images are not equal");

            FileLogger.info("Image comparison passed. Expected image: " + expectedImageName);
        } catch (Exception e) {
            FileLogger.error("Image comparison failed. Expected image: " + expectedImageName, e);
            throw new AssertionError("Image comparison failed.", e);
        }
    }

    @Step("Validate count views order after sorting")
    public static boolean validateDescendingOrder() {
        FileLogger.info("Validating count views after sorting");
        //Let's validate that the count views after sorting are sorted from high to low (Descending order)
        return
        HelpersMethods.isDescendingSorted(ScenarioContext.getFromDataStore(ScenarioContext.countViewsAfterSorting));
    }


    @Step("Verify text with text")
    public static void text(String actualText, String expectedText) {
        try {
            assertEquals(actualText, expectedText);
            FileLogger.info("Text verification passed. Actual: " + actualText + ", Expected: " + expectedText);
        } catch (AssertionError e) {
            FileLogger.error("Text verification failed. Actual: " + actualText + ", Expected: " + expectedText, e);
            throw e; // Re-throw the AssertionError to mark the test as failed in your testing framework
        }
    }

}
