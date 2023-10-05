package WorkFlows;

import Extensions.UiActions;
import Extensions.Verifications;
import Utilities.CommonOperations;
import io.qameta.allure.Step;

import static PageObjects.MainPage.*;
import static PageObjects.CustomerLoginPage.*;
import static PageObjects.CustomerPage.*;
import static PageObjects.CustomerTransactionsPage.*;

public class XyzBankFlows extends CommonOperations {

    private static int TIMEOUT_IN_MILLIS = 20000;
    @Step("Run XYZ Bank user account validation flow")
    public static void runXyzBankUserAccountValidationFlow() {
        //Click on 'Customer Login' button
        UiActions.click(customerLogin);
        UiActions.waitUntilElementIsVisible(form, Integer.valueOf(getData("TimeOut")));

        //Select Harry Potter user from the selection list by index
        UiActions.selectFromDropDownByValue(usersSelector, "2");

        //Click on Login button
        UiActions.click(loginButton);

        //Validate the account name next to the 'welcome' text
        Verifications.validateTextInElement(welcomeTitle, getData("selectedUser"));

        //Click on transactions button and validate that there is no transactions records in the table
        UiActions.click(transactionsButton);
        UiActions.waitUntilElementIsVisible(table, Integer.valueOf(getData("TimeOut")));
        Verifications.validateTableIsEmpty(tableRows);

        //Click on back button
        UiActions.click(backButton);
    }

    @Step("Make deposit and withdraw and validate transactions")
    public static void validateUserTransactions() {
        //These steps were copied from a previous test so that this test can run independently
        UiActions.click(customerLogin);
        UiActions.waitUntilElementIsVisible(form, Integer.valueOf(getData("TimeOut")));
        UiActions.selectFromDropDownByValue(usersSelector, "2");
        UiActions.click(loginButton);
        Verifications.validateTextInElement(welcomeTitle, getData("selectedUser"));

        //This is the point that the test is starting, we want to make user deposit and withdraw and validate the user actions
        UiActions.waitUntilElementIsVisible(depositButton, Integer.valueOf(getData("TimeOut")));

        //Saving the initial account balance value is done to ensure that the account balance is not assumed to be zero when the test is running
        int initialBalance = Integer.valueOf(UiActions.getElementValue(accountBalance));

        //Here, the user makes a deposit of an amount that is read from the configuration file
        UiActions.click(depositButton);
        UiActions.waitUntilElementIsVisible(amountInputField, Integer.valueOf(getData("TimeOut")));
        UiActions.fillInputField(amountInputField, getData("depositAmount"));
        UiActions.click(transactionSubmitButton);
        UiActions.waitUntilElementIsVisible(transactionMessage, Integer.valueOf(getData("TimeOut")));

        //Validating the deposit action by reading the confirmation message and validating that the account balance changed according to the deposit amount
        Verifications.validateTextInElement(transactionMessage, "Deposit Successful");
        Verifications.validateTextInElement(accountBalance, String.valueOf(initialBalance+Integer.valueOf(getData("depositAmount"))));

        //Here, the user makes a withdrawal of an amount that is read from the configuration file
        UiActions.click(withdrawButton);
        UiActions.waitUntilElementIsVisible(amountInputLabel, Integer.valueOf(getData("TimeOut")));
        UiActions.waitUntilElementIsVisible(amountInputField, Integer.valueOf(getData("TimeOut")));
        UiActions.fillInputField(amountInputField, getData("withdrawAmount"));
        UiActions.click(transactionSubmitButton);
        UiActions.waitUntilElementIsVisible(transactionMessage, Integer.valueOf(getData("TimeOut")));

        //Validating the withdrawal action by reading the confirmation message and validating that the account balance changed according to the withdrawal amount
        Verifications.validateTextInElement(accountBalance, String.valueOf(initialBalance+Integer.valueOf(getData("withdrawAmount"))));
        Verifications.validateTextInElement(transactionMessage, "Transaction successful");
        UiActions.waitWithCounter(TIMEOUT_IN_MILLIS);
        UiActions.click(transactionsButton);
        UiActions.waitUntilElementIsVisible(table, Integer.valueOf(getData("TimeOut")));

        //Validation of the customer transaction in the transaction table
        Verifications.validateTransactionTableDisplayData(tableRows);

        //Here are 2 validation methods to validate the data in the transaction table, both methods are functional.
        //The first method is based on TestNG library (currently marked, you can select what method to use)
        //Verifications.validateAmountAndTransactionType();

        //The second method is based on Junit library:
        Verifications.validateAmountAndTransactionTypeInTransactionsTable();
    }

}
