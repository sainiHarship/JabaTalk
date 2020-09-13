package modules;

import com.codeborne.selenide.Selenide;
import locators.CreateAccountRepo;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Common;
import org.testng.Assert;
import utils.EmailUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountPage {
    public static void checkLanguageDropdown() throws Exception {
        List<String> actualLang = new ArrayList<>();
        Common.ClickElement(CreateAccountRepo.chooseLanguage,"Click language dropdown");
        List<WebElement> languageOptions = Common.FindAllElements(CreateAccountRepo.languageOptions);
        for(int i=0;i<languageOptions.size();i++){
            actualLang.add(languageOptions.get(i).getText());
        }
        Assert.assertTrue(actualLang.contains("English"));
        Assert.assertTrue(actualLang.contains("Dutch"));
    }

    public static void createAccount(String Name, String orgName, String email) throws Exception {
        String lastName = Common.getRandomAlphaNumericString(4);
        String fullName = Name+" "+lastName;
        Common.ClearAndSendKeys(CreateAccountRepo.fullName,fullName, "Full Name");
        Common.ClearAndSendKeys(CreateAccountRepo.orgName,orgName, "Organisation Name");
        Common.ClearAndSendKeys(CreateAccountRepo.signUpEmail,email, "signup email");
        WebElement element = Common.FindAnElement(CreateAccountRepo.signUpAgree);
        Boolean selected = (Boolean) ((JavascriptExecutor) Common.getDriverInstance()).executeScript("return arguments[0].checked;", element);
        if (!selected){
            Common.ClickElement(CreateAccountRepo.signUpAgree, "Agree Terms & Conditions");
        }
        Common.ClickElement(CreateAccountRepo.signUpBtn,"Get Started");
    }

    public static void isEmailReceived(EmailUtils emailUtils,String fullName) throws Exception {
        boolean emailReceived = emailUtils.messagePresentValidation(fullName);
        Assert.assertTrue(emailReceived,"Email received for signed up account");
    }
}
