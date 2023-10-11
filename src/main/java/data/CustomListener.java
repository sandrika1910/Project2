package data;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener extends TestConfigClass implements ITestListener{
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(WebDriverRunner.getWebDriver());
        saveLogs(result.getMethod().getConstructorOrMethod().getName());
    }
    @Attachment(value="Screenshot", type="img/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value="Stacktrace", type="text/plain")
    public static String saveLogs(String message) {
        return message;
    }
}
