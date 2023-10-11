package steps;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import data.CustomListener;
import io.qameta.allure.Step;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import pages.FirstProductPage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Configuration.assertionMode;
import static com.codeborne.selenide.Selenide.$;

@Listeners({CustomListener.class})
public class FirstProductPageSteps {
    FirstProductPage fpp = new FirstProductPage();

    @Step("Step -> click on 'გაზიარება'")
    public FirstProductPageSteps clickOnShare() {
        fpp.shareButton.hover().click();
        return this;
    }

    @Step("Step -> check if facebook login page appear")
    public FirstProductPageSteps checkWindow() {
        Selenide.switchTo().window("Facebook");
        $("body").should(exist);
        String expectedLink = "https://www.facebook.com/login.php?skip_api_login=1&api_key=966242223397117&signed_next=1&next=https%3A%2F%2Fwww.facebook.com%2Fsharer%2Fsharer.php%3Fu%3Dhttps%253A%252F%252Fwww.swoop.ge%252Foffers%252F427218%252Fhit-seti-adgilze-mitanit%252Frestornebi-da-barebi%252Fsushi&cancel_url=https%3A%2F%2Fwww.facebook.com%2Fdialog%2Fclose_window%2F%3Fapp_id%3D966242223397117%26connect%3D0%23_%3D_&display=popup&locale=en_US";
//        softAssert.assertEquals(WebDriverRunner.url(),expectedLink, "There is no appearing login page");
        assert WebDriverRunner.url().equals(expectedLink);
        return this;
    }
}
