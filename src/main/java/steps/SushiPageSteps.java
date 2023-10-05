package steps;

import com.codeborne.selenide.*;
import data.CustomListener;
import data.HelperClassForSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import pages.SushiPage;

import static com.codeborne.selenide.Configuration.assertionMode;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Listeners({CustomListener.class})
public class SushiPageSteps {
    SushiPage sushiPage = new SushiPage();


    @Step("Step -> Add first product to favorites")
    public SushiPageSteps addToFavorites() {
        sushiPage.container.$(".deal-box-wishlist").hover().click(); // Add first item to favorites
        return this;
    }

    @Step("Step -> Check if Login and Register form has appeared")
    public SushiPageSteps checkIfLoginFormAppear() {
        SelenideElement loginRegisterForm = $("section[class='login-register-outer']");
        loginRegisterForm.shouldHave(Condition.attribute("style", "right: 0px;"));
//        softAssert.assertEquals(loginRegisterForm.getAttribute("style"), "right: 0px;", "Login and Register form did not appear.");
        return this;
    }

    @Step("Step -> check that all vouchers have not sold yet")
    public SushiPageSteps checkVoucher() {
        SelenideElement voucherLimit = sushiPage.container.$("div[class='voucher-limit']");
//        softAssert.assertNotEquals(voucherLimit.getAttribute("style"),"width: 100%;", "All vouchers sold!");
        voucherLimit.shouldNotHave(Condition.attribute("style", "width: 100%;"));
//        softAssert.assertAll();
        return this;
    }

    @Step("Step -> sort by 'ფასით კლებადი'")
    public SushiPageSteps clickOnSort() {
        sushiPage.sortProductsElement.click();
        Select select = new Select(sushiPage.sortProductsElement);
        select.selectByVisibleText("ფასით კლებადი");
        return this;
    }

    @Step("Step -> wait for to reload DOM to sort ")
    public SushiPageSteps waitForSort() {
        String targetUrl = "https://www.swoop.ge/category/235/restornebi-da-barebi/sushi/?sortID=1https://www.swoop.ge/category/235/restornebi-da-barebi/sushi/?sortID=1";
        long startTime = System.currentTimeMillis();
        long maxDuration = 7000; // 7 seconds in milliseconds

        while ((System.currentTimeMillis() - startTime) >= maxDuration) {
            if (WebDriverRunner.url().equals(targetUrl)) {
                break;
            }
        }
        return this;
    }

    @Step("Step -> check that first product price is greater than second product price")
    public SushiPageSteps checkFirstAndSecondProductsPrices() {
        ElementsCollection collection = $$("div[class='discounted-prices']");
        int firstItemPrice = HelperClassForSteps.getNumbersFromString(collection.get(0).$("p").getText());
        int secondItemPrice = HelperClassForSteps.getNumbersFromString(collection.get(1).$("p").getText());
//        softAssert.assertTrue(firstItemPrice > secondItemPrice,"First product's price is not greater than second product's price");
        assert firstItemPrice > secondItemPrice;
        return this;
    }

    @Step("Step -> click on first product on 'სუში' page")
    public SushiPageSteps clickFirstProduct() {
        sushiPage.firstProduct.hover().click();
        return this;
    }

}
