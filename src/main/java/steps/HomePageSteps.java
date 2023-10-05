package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.HomePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePageSteps {
    HomePage homePage = new HomePage();

    @Step("Step -> close cookies")
    public HomePageSteps closeCookies() {
        homePage.cookieButton.hover().click();
        return this;
    }

    @Step("Step -> click on 'დასვენება'")
    public HomePageSteps goToRecreationPage() {
        homePage.recreationButton.hover().click();
        return this;
    }

    @Step("Step -> click on 'კატეგორიები'")
    public HomePageSteps clickOnCategories() {
        SelenideElement categories = homePage.headerDiv.$(".NewCategories.newcat");
        categories.hover().click();
        homePage.body.shouldHave(Condition.attribute("class", "fixed"));
        return this;
    }

    @Step("Step -> hover on 'ჭამა'")
    public HomePageSteps hoverOnEat() {
        SelenideElement leftSideCategories = $("div[class='LeftSideCategories']");
        SelenideElement eat = leftSideCategories.$("li[cat_id='CatId-3']");
        eat.hover();
        eat.shouldHave(Condition.attribute("class", "leftSideMenu catId-3 hovered"));
        return this;
    }

    @Step("Step -> click on 'სუში'")
    public HomePageSteps clickOnSushi() {
        SelenideElement sushi = $x("//a[text()='სუში']");
        sushi.click();
        return this;
    }

    @Step("Step -> click on 'შესვლა'")
    public HomePageSteps clickOnLogin() {
        $x("//p[text()='შესვლა']").hover().click();
        homePage.loginRegisterForm.shouldHave(Condition.attribute("style", "right: 0px;"));
        return this;
    }

}
