package Utilities;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.testng.Assert.fail;

public class HelpersMethods extends CommonOperations
{
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

    public static void saveResponseToJson(String json, String fileName, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath + fileName + ".json");
            fileWriter.write(json);
            fileWriter.close();

            System.out.println("Response saved to " + fileName + ".json in" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
