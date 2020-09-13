package locators;

import org.openqa.selenium.By;

public class CreateAccountRepo {

    public static By chooseLanguage = By.xpath("//span[contains(@class,'ui-select-placeholder')][text()='Choose Language']/ancestor::span");
    public static By languageOptions = By.xpath("//a[contains(@class,'ui-select-choices')]//div");
    public static By langEnglish = By.xpath("//a[contains(@class,'ui-select-choices')]//div[text()='English']");
    public static By fullName = By.cssSelector("input#name");
    public static By orgName = By.cssSelector("input#orgName");
    public static By signUpEmail = By.cssSelector("input#singUpEmail");
    public static By signUpAgree = By.cssSelector("label.ui-checkbox span");
    public static By signUpBtn = By.cssSelector("div[class *='form-group'] button");
    public static By emailSentMessage = By.cssSelector("div.alert span");

}
