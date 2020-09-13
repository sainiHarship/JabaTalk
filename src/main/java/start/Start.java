package start;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import urls.Urls;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.open;

public class Start {

    public static WebDriver driver;

    public static WebDriver getDriverInstance(){
        return WebDriverRunner.getWebDriver();
    }

    protected static void InitializeBrowser(){
        //Selenide Configuration
        Configuration.fastSetValue = true;
        Configuration.screenshots = false;
        Configuration.startMaximized = true;
        Configuration.timeout = 15000;
        String downloadFilepath = System.getProperty("user.dir");
        ChromeDriverManager.chromedriver().version("84").setup();

        Configuration.browser = WebDriverRunner.CHROME;
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.default_directory", downloadFilepath);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);

        // Opening url
        open(Urls.baseUrl);

    }
}