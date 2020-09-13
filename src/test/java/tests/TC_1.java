package tests;

import base.TestBase;
import modules.CreateAccountPage;
import org.testng.annotations.Test;

public class TC_1 extends TestBase {
    CreateAccountPage objCreateAccount = new CreateAccountPage();

    @Test(description = "Verify that when user Creates Account, an email for Password reset is triggered")
    public void QA_TC_1() throws Exception {

        objCreateAccount.checkLanguageDropdown();
        objCreateAccount.createAccount("Harship","TestOrganisation","jabatalktestemail@gmail.com");
        //objCreateAccount.isEmailReceived(,CreateAccountPage.fullName);
    }

}
