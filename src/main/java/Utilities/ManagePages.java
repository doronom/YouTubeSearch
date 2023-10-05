package Utilities;

import PageObjects.MainPage;
import org.openqa.selenium.support.PageFactory;

//This class is the management of the page objects
public class ManagePages extends Base {

    public static void init() {
        xyzBankLogin = PageFactory.initElements(driver, MainPage.class);
        xyzBankCustomerLogin = PageFactory.initElements(driver, PageObjects.CustomerLoginPage.class);
        xyzBankCustomerPage = PageFactory.initElements(driver, PageObjects.CustomerPage.class);
        xyzBankCustomerTransactionsPage = PageFactory.initElements(driver, PageObjects.CustomerTransactionsPage.class);
    }
}
