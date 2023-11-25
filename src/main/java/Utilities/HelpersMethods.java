package Utilities;

import Extensions.FileLogger;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import java.math.BigDecimal;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.fail;

public class HelpersMethods extends CommonOperations
{
    public static List<Double> saveViewCountToList(List<WebElement> countList) {
        List<Double> viewCountList = new ArrayList<>();
        for (WebElement item : countList) {
            String countText = item.getAttribute("innerText")
                    .replaceAll("[^\\d.MK]", ""); // Keep only numeric, 'M', and 'K' characters

            try {
                BigDecimal count = new BigDecimal(countText.substring(0, countText.length() - 1));

                if (countText.endsWith("M")) {
                    count = count.multiply(BigDecimal.valueOf(1_000_000)); // Convert 'M' to millions
                } else if (countText.endsWith("K")) {
                    count = count.multiply(BigDecimal.valueOf(1_000)); // Convert 'K' to thousands
                }

                viewCountList.add(count.doubleValue());
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                FileLogger.error("Failed to parse view count: " + countText);
            }
        }
        return viewCountList;
    }


    //This is a help function - Taking reference screenshot before starting the test
    public static void takeElementScreenShot(WebElement imgElm, String imgName)
    {
        imageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, imgElm);
       try
       {
           ImageIO.write(imageScreenshot.getImage(), "png", new File(getData("ImageRepo") + imgName + ".png"));
       }
       catch (Exception e)
       {
           System.out.println("Error writing image file, see details"  + e);
           fail("Take screenshot failed " + e.getMessage());
       }
    }

    // Check if a list is sorted in descending order
    public static boolean isDescendingSorted(List<Double> list) {
        List<Double> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList, Collections.reverseOrder());
        return list.equals(sortedList);
    }
}
