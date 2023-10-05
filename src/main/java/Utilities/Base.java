package Utilities;

import PageObjects.MainPage;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
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
    public static MainPage xyzBankLogin;

    public static PageObjects.CustomerLoginPage xyzBankCustomerLogin;

    public static PageObjects.CustomerPage xyzBankCustomerPage;
    public static PageObjects.CustomerTransactionsPage xyzBankCustomerTransactionsPage;

    public static RequestSpecification httpRequest;
    public static Response response;
    public static JSONObject requestParams = new JSONObject();
    public static JsonPath jp;
}
