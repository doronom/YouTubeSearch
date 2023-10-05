package Helpers;

import Utilities.CommonOperations;
import org.testng.annotations.Test;
import WorkFlows.XyzBankFlows;

//This class demonstrate an automation visual testing

public class VisualTesting extends CommonOperations
{
    @Test
    public void takeScreenShot()
    {
        XyzBankFlows.runXyzBankUserAccountValidationFlow();
    }
}
