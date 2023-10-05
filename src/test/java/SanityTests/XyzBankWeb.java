package SanityTests;

import Utilities.CommonOperations;
import WorkFlows.XyzBankFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utilities.Listeners.class)
public class XyzBankWeb extends CommonOperations {
    @Test(description = "Test 01: Run user account validation flow")
    @Description("Test Description: Run User flow - login and validate that the user has no transactions")
    public void test01_login() {
        XyzBankFlows.runXyzBankUserAccountValidationFlow();
    }
    @Test(description = "Test 02: Validate user transactions")
    @Description("Test Description: Validate user transactions - make deposit and withdraw and validate transaction in the transactions table")
    public void test02_validateTransactions() {
        XyzBankFlows.validateUserTransactions();
    }
}
