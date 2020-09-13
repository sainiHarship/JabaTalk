package locators;

import org.openqa.selenium.By;

public class CreateAccountRepo {

    public static By chooseLanguage = By.xpath("//span[contains(@class,'ui-select-placeholder')][text()='Choose Language']");
    public static By languageOptions = By.xpath("//a[contains(@class,'ui-select-choices')]//div");
    public static By fullName = By.cssSelector("input#name");
    public static By orgName = By.cssSelector("input#orgName");
    public static By signUpEmail = By.cssSelector("input#singUpEmail");
    public static By signUpAgree = By.cssSelector("input[ng-model='signUp.agree']");
    public static By signUpBtn = By.cssSelector("div[class *='form-group'] button");

}
