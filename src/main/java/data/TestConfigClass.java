package data;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.fastSetValue;

public class TestConfigClass {
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        browserSize = null;
        baseUrl="https://www.swoop.ge/";
        timeout=200000;
        pageLoadTimeout=750000;
//        assertionMode= AssertionMode.SOFT;
        holdBrowserOpen=false;
        reopenBrowserOnFail=true;
        savePageSource=false;
        fastSetValue=true;
    }

    public void tearDown() {
//        Selenide.closeWebDriver();
        WebDriverRunner.closeWebDriver();
    }
}
