package utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import start.Start;
import urls.Urls;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.Thread.sleep;

public class Common extends Start {

    public static final long DEFAULT_WAIT = 90000;
    public static final long DEFAULT_IMPLICIT_WAIT_IN_SEC = 15;

    public static void Init() throws Exception {
        InitializeBrowser();
    }

    public static boolean IsElementExists(By by) {
        try {
            sleep(2000);
            return $(by).exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static void ClickElement(By Element, String detail) throws Exception {
        try {
            Log.info("Clicking on:" + detail);
            $(Element)
                    .waitUntil(appear, DEFAULT_WAIT)
                    .click();
        } catch (Exception e) {
            Log.error("Error on:" + detail);
            Log.error("There is exception: " + e.getMessage());
            throw e;

        }
    }

    public static List<WebElement> FindAllElements(By Element) throws Exception {
        List<WebElement> TempElement = null;
        try {

            TempElement = getWebDriver().findElements(Element);
            return TempElement;
        } catch (Exception e) {
            Log.error("There is exception: " + e.toString());
            throw e;
        }

    }

    public static WebElement FindAnElementByExactText(String text) {
        return $(byText(text)).waitUntil(appear, DEFAULT_WAIT).getWrappedElement();
    }

    public static WebElement FindAnElementByTextContains(String text) {
        return $(withText(text)).waitUntil(appear, DEFAULT_WAIT).getWrappedElement();
    }

    public static WebElement FindAnElement(By Element) {
        WebElement TempElement = null;
        try {
            TempElement = $(Element)
                    .waitUntil(appear, DEFAULT_WAIT).getWrappedElement();
        } catch (Exception e) {
            Log.error("There is an exception: " + e.toString());
            throw e;
        }
        return TempElement;
    }

    public static void SwitchToFrame(WebElement frames, int interval) throws Exception {
        try {
            switchTo().frame(frames);
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            Log.error("Problem in Switching to frame ");
            Log.error(e.getMessage());
            throw e;
        }

    }

    public static void ClearAndSendKeys(By Element, String KeysToSend, String Detail) throws Exception {
        try {
            Log.info("Input " + Detail);
            WebElement elm = $(Element)
                    .waitUntil(appear, DEFAULT_WAIT)
                    .waitUntil(enabled, DEFAULT_WAIT);
            elm.clear();
            elm.sendKeys(KeysToSend);

        } catch (Exception e) {
            Log.error("Error in setting a text value: " + e.toString());
            throw e;
        }
    }

    public static void sendKeys(By Element, Keys enter, String Detail) {
        try {
            Log.info(Detail);
            WebElement element = $(Element)
                    .waitUntil(appear, DEFAULT_WAIT)
                    .waitUntil(enabled, DEFAULT_WAIT);
            element.sendKeys(enter);

        } catch (Exception e) {
            Log.error("Error in send Keys" + Element.toString());
            Log.error(e.getMessage());
            throw e;

        }
    }

    public static void SelectDropdownText(By Dropdown, String TextToSelect) {
        try {
            Select drpToSelectFrom = new Select(getWebDriver().findElement(Dropdown));
            drpToSelectFrom.selectByVisibleText(TextToSelect);
        } catch (Exception e) {
            Log.error("There is an exception: " + e.toString());
        }
    }

    public static void SelectDropdownText(WebElement Dropdown, String TextToSelect) {
        try {
            Select drpToSelectFrom = new Select(Dropdown);
            drpToSelectFrom.selectByVisibleText(TextToSelect);
        } catch (Exception e) {
            Log.error("There is an exception: " + e.toString());
        }
    }

    public static void setInputValueUsingJS(By by, String value) {
        WebElement elm = $(by).waitUntil(appear, DEFAULT_WAIT);
        String js = String.format("arguments[0].value=\"%s\"", value);
        executeJavaScript(js, elm);
    }

    public static void javascriptExecutorClick(WebElement Element, int Interval) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click();", Element);
        try {
            Thread.sleep(Interval);
        } catch (Exception e) {
            Log.error("There is an exception: " + e.toString());
        }
    }
    public static void SwitchToDefaultContent(int Interval) {
        try {
            System.out.println("Switching default");
            getWebDriver().switchTo().defaultContent();
            Thread.sleep(Interval);
        } catch (Exception e) {
            Log.error("There is an exception: " + e.toString());
        }
    }

    public static void RefreshPage(int Interval) {
        try {
            Thread.sleep(6000);
            RefreshPage();
            Thread.sleep(Interval);
        } catch (Exception e) {
            Log.error("There is an exception: " + e.toString());
        }
    }

    public static void RefreshPage() throws Exception {
        getWebDriver().navigate().refresh();
        sleep(5000l);
    }

    public static void doubleClick(By Element) throws InterruptedException {
        try {
            Actions action = new Actions(getWebDriver());
            action.doubleClick(getWebDriver().findElement(Element)).build().perform();
            //action.doubleClick(driver.findElement(By.xpath("//span[contains(.,'"+SendRequestComments+"')]"))).build().perform();
        } catch (Exception e) {
            Log.error("There is an exception: " + e.toString());
        }

    }

    public static void closeBrowser() {
        try {
            if (getWebDriver() != null) {
                getWebDriver().quit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void alert(int Interval) throws InterruptedException {
        Alert alert = getWebDriver().switchTo().alert();
        sleep(3000);
        alert.accept();
    }

    public static void switchToActiveElementString(String Element, int Interval) {
        getWebDriver().switchTo().activeElement().sendKeys(Element);
    }

    public static void switchToActiveElementKeys(Keys tab) {
        getWebDriver().switchTo().activeElement().sendKeys(tab);
    }

    public static void SwitchToWindowHandle(String Element) {
        getWebDriver().switchTo().window(Element);
    }

    public static String WindowHandle() {
        return getWebDriver().getWindowHandle();
    }

    public static Set<String> WindowHandles() {
        getWebDriver().getWindowHandles();
        return null;
    }

    public static Set<String> getWindowHandles() {
        return getWebDriver().getWindowHandles();
    }

    public static void Action(WebElement Element) {
        Actions act = new Actions(getWebDriver());
        act.moveToElement(Element);
    }

    public static void ActionClick(WebElement Element) {
        Actions act = new Actions(getWebDriver());
        act.moveToElement(Element).click();
    }

    public static void switchFrame(By Element, int Interval) throws InterruptedException {
        getWebDriver().switchTo().frame(getWebDriver().findElement(Element));
        sleep(Interval);

    }

    public static void deleteCookies(int Interval) throws InterruptedException {
        getWebDriver().manage().deleteAllCookies();
        sleep(Interval);
    }

    public static String getElementText(By Element, int timeOutInSeconds) {
        return $(Element).waitUntil(appear, DEFAULT_WAIT).getText();

    }

    public static void ClickElementByIndex(By Element, int index) {
        try {
            getWebDriver().findElements(Element).get(index).click();
        } catch (Exception e) {
            Log.error("Element is not clickable: " + e.toString());
        }
    }

    public static String CaptureScreenForReport_Base64() {
        TakesScreenshot oScn = (TakesScreenshot) WebDriverRunner.getWebDriver();
        return oScn.getScreenshotAs(OutputType.BASE64);
    }
    public static void url() {
        getWebDriver().get(Urls.baseUrl);
    }

    public static String GetCurrentUrl() {
        if (getWebDriver() != null) {
            return getWebDriver().getCurrentUrl();
        } else
            return "";

    }

    public static String getRandomAlphaNumericString(int length) {
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString = new String(array, Charset.forName("UTF-8"));
        StringBuffer r = new StringBuffer();
        for (int k = 0; k < randomString.length(); k++) {

            char ch = randomString.charAt(k);

            if (((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) && (length > 0)) {
                r.append(ch);
                length--;
            }
        }
        return r.toString();
    }

}
