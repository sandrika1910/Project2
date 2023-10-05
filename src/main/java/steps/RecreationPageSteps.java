package steps;

import com.codeborne.selenide.*;
import data.CustomListener;
import data.HelperClassForSteps;
import io.qameta.allure.Step;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import pages.RecreationPage;

import static com.codeborne.selenide.Configuration.assertionMode;
import static com.codeborne.selenide.Selenide.$;
@Listeners({CustomListener.class})
public class RecreationPageSteps {
    RecreationPage rp = new RecreationPage();
    private SoftAssert softAssert = new SoftAssert();
    public RecreationPageSteps() {
        assertionMode=AssertionMode.SOFT;
    }

    @Step("Step -> scroll down")
    public RecreationPageSteps scrollDown() {
        Selenide.executeJavaScript("arguments[0].scrollTo(0, arguments[0].scrollHeight);", rp.sidebar);
        rp.sidebar.scrollTo();
        return this;
    }

    @Step("Step -> close advertise banner")
    public RecreationPageSteps closeBanner() {
        rp.banner.shouldHave(Condition.attribute("style", "display: unset;"));
        rp.bannerClose.click();
        rp.banner.shouldNot(Condition.exist);
        return this;
    }

    @Step("Step -> scroll down again")
    public RecreationPageSteps scrollDown2() {
        long windowHeight = Selenide.executeJavaScript("return window.innerHeight;");
        long scrollPixels = windowHeight/3;
        Selenide.executeJavaScript("window.scrollBy(0, arguments[0]);", scrollPixels);
        return this;
    }

    @Step("Step -> set price range value from 200 to 230")
    public RecreationPageSteps setPriceRange() {
        SelenideElement findButton = rp.parentElement.$("div[class='submit-button']"),
                minPrice = rp.parentElement.$("#minprice"),
                maxPrice = rp.parentElement.$("#maxprice");
        minPrice.sendKeys("200");
        maxPrice.sendKeys("230");
        findButton.click();
        return this;
    }

    @Step("Step -> wait to load page after setting min and max price range")
    public RecreationPageSteps waitForLoadPage() {
//        $("div[class='freeze']").should(Condition.exist);
//        $("div[class='freeze']").should(Condition.not(Condition.exist));
        String targetUrl = "https://www.swoop.ge/category/24/dasveneba/?minprice=200&maxprice=230";
        long startTime = System.currentTimeMillis();
        long maxDuration = 7000; // 7 seconds in milliseconds

        while( (System.currentTimeMillis() - startTime) >= maxDuration){
            if(WebDriverRunner.url().equals(targetUrl)) {
                break;
            }
        }
        return this;
    }

    @Step("Step -> check that return products prices are in range of 200 to 230")
    public RecreationPageSteps checkPriceRangeSetResult() {
        SelenideElement items = $("#partialid");

        ElementsCollection collection = items.$$(".discounted-prices");
        for (int i = 0; i < collection.size(); i++) {
            String price = collection.get(i).$("p").getText();
            int priceInt = HelperClassForSteps.getNumbersFromString(price);
            softAssert.assertTrue(priceInt >= 200 && priceInt <= 230, "Price is not in range from 200 to 230!");
        }

//        softAssert.assertAll();
        return this;
    }
}
