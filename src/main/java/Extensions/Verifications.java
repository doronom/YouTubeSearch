package Extensions;

import Utilities.CommonOperations;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

import static PageObjects.CustomerTransactionsPage.*;
import static org.testng.Assert.assertFalse;

public class Verifications extends CommonOperations {

    private static int RETRY_INTERVAL_IN_SECONDS = 10;
    @Step("Verify text in element")
    public static void validateTextInElement(WebElement element, String expectedValue) {
        Assert.assertEquals(element.getText(), expectedValue);
    }

    //This method read the previous version image logo and compare it to the new version image logo
    @Step("Compare main image logo")
    public static void visualElement(WebElement imageElement, String expectedImageName)
    {
        BufferedImage expectedImage = null;     //initializing expected image to 'BufferImage' object
        try
        {
            //Read the image that already taken (previous version) and store in 'BufferImage' object
            expectedImage = ImageIO.read(new File(getData("ImageRepo")+ expectedImageName + ".png"));
        }
        catch (Exception e)
        {
            System.out.println("Error reading image file: " + e);
        }
        //Take the new image in the current page version and compare it to the image from previous version
        Screenshot imageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, imageElement);
        BufferedImage actualImage = imageScreenshot.getImage();
        dif = imgDif.makeDiff(actualImage, expectedImage);  // compare the actual image to the expected image
        assertFalse(dif.hasDiff(), "images are not equals");
    }

    @Step("Validate table is empty")
    public static boolean validateTableIsEmpty(List<WebElement> elements) {
        if (elements.isEmpty()) {
            FileLogger.info("Table is empty");
            return true;
        } else {
            FileLogger.info("Table is not empty");
            return false;
        }
    }

    @Step("Validate that the transaction table display customer transaction data")
    public static void validateTransactionTableDisplayData(List<WebElement> elements) {
        try {
            BooleanSupplier transactionDataDisplayed = () -> {
                boolean tableDataExist = !validateTableIsEmpty(elements);
                // If table data doesn't exist, refresh the page and wait
                if (!tableDataExist) {
                    UiActions.refreshPageAndWait(RETRY_INTERVAL_IN_SECONDS);
                }

                return tableDataExist;
            };

            // Wait until the condition (table data displayed) is met or timeout occurs
            UiActions.waitUntilConditionMet(transactionDataDisplayed, 30);
        } catch (Exception e) {
            FileLogger.info("Failed to wait for table data to be displayed");
            e.printStackTrace();
        }
    }

    @Step("Validate amount and transaction type")
    public static void validateAmountAndTransactionType() {
        FileLogger.info("Validating customers transactions in the transaction table");
        String firstRowAmount = firstRowAmountColumn.getText();
        String firstRowTransactionType = firstRowTransactionTypeColumn.getText();

        Assert.assertEquals(firstRowAmount, getData("depositAmount"));
        Assert.assertEquals(firstRowTransactionType, "Credit");

        String secondRowAmount = secondRowAmountColumn.getText();
        String secondRowTransactionType = secondRowTransactionTypeColumn.getText();

        Assert.assertEquals(secondRowAmount, getData("withdrawAmount"));
        Assert.assertEquals(secondRowTransactionType, "Debit");
    }

    @Step("Validate amount and transaction type in transaction table")
    public static void validateAmountAndTransactionTypeInTransactionsTable() {
        // Define predicates to match first row's amount and transaction type
        Predicate<WebElement> matchingFirstRowAmount = new ColumnContainsPredicate(firstRowAmountColumn, getData("depositAmount"));
        Predicate<WebElement> matchingFirstRowTransactionType = new ColumnContainsPredicate(firstRowTransactionTypeColumn, "Credit");

        // Define predicates to match second row's amount and transaction type
        Predicate<WebElement> matchingSecondRowAmount = new ColumnContainsPredicate(secondRowAmountColumn, getData("withdrawAmount"));
        Predicate<WebElement> matchingSecondRowTransactionType = new ColumnContainsPredicate(secondRowTransactionTypeColumn, "Debit");

        // Combine predicates for the first and second rows
        Predicate<WebElement> matchingFirstRaw = matchingFirstRowAmount.and(matchingFirstRowTransactionType);
        Predicate<WebElement> matchingSecondRaw = matchingSecondRowAmount.and(matchingSecondRowTransactionType);

        // Create a BooleanSupplier to check if both conditions are met
        BooleanSupplier matchTableFound = () -> {
            // Refresh the page and wait
            UiActions.refreshPageAndWait(RETRY_INTERVAL_IN_SECONDS);

            // Check if both conditions are met for the table rows
            return UiActions.checkRowExistsInTable(table, matchingFirstRaw, null) &&
                    UiActions.checkRowExistsInTable(table, matchingSecondRaw, null);
        };
        FileLogger.info("Validating customers transactions in the transaction table");
        // Wait until the conditions are met within the specified timeout
        UiActions.waitUntilConditionMet(matchTableFound, 30);
    }

}
