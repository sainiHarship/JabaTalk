package tests;

import base.TestBase;
import modules.CreateAccountPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.EmailUtils;

public class TC_1 extends TestBase {
    CreateAccountPage objCreateAccount = new CreateAccountPage();


    @Test(description = "Verify that when user Creates Account, an email for Password reset is triggered")
    public void QA_TC_1() throws Exception {

        objCreateAccount.checkLanguageDropdown();
        objCreateAccount.createAccount("Harship","TestOrganisation","jabatalktestemail@gmail.com");
        boolean emailReceived = EmailUtils.messagePresentValidation(CreateAccountPage.fullName);
        Assert.assertTrue(emailReceived, "Email Received : TC Pass");
    }

}
