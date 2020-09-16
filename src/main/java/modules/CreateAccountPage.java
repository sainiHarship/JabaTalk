package modules;

import com.codeborne.selenide.Selenide;
import locators.CreateAccountRepo;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Common;
import org.testng.Assert;
import utils.EmailUtils;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static utils.Common.sync;

public class CreateAccountPage {
    public static String fullName=null;

    public void checkLanguageDropdown() throws Exception {
        List<String> actualLang = new ArrayList<>();
        Common.ClickElement(CreateAccountRepo.chooseLanguage,"Click language dropdown");
        List<WebElement> languageOptions = Common.FindAllElements(CreateAccountRepo.languageOptions);
        for(int i=0;i<languageOptions.size();i++){
            actualLang.add(languageOptions.get(i).getText());
        }
        Assert.assertTrue(actualLang.contains("English"));
        Assert.assertTrue(actualLang.contains("Dutch"));
        Common.ClickElement(CreateAccountRepo.langEnglish,"Select English option");
    }

    public void createAccount(String Name, String orgName, String email) throws Exception {
        String lastName = Common.getRandomAlphaNumericString(4);
        fullName = Name+" "+lastName;
        String emailSuccessMsg = " A welcome email has been sent. Please check your email. ";
        Common.ClearAndSendKeys(CreateAccountRepo.fullName,fullName, "Full Name");
        Common.ClearAndSendKeys(CreateAccountRepo.orgName,orgName, "Organisation Name");
        Common.ClearAndSendKeys(CreateAccountRepo.signUpEmail,email, "signup email");
        Common.ClickElement(CreateAccountRepo.signUpAgree, "Agree Terms & Conditions");
        Common.ClickElement(CreateAccountRepo.signUpBtn,"Get Started");
        sync(20000l);
        //WebElement element = $("div.alert span").waitUntil(appear, 5000l).shouldBe(exist);
    }

    public void isEmailReceived(EmailUtils emailUtils,String fullName) throws Exception {
        boolean emailReceived = emailUtils.messagePresentValidation(fullName);
        Assert.assertTrue(emailReceived,"Email received for signed up account");
    }
}
