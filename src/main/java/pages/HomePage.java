package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    public SelenideElement recreationButton = $$("li[class='MoreCategories']").get(2),
        cookieButton = $("div[class='cookieButton']"),
        headerDiv = $(".HeaderContainer.container"),
        body = $("body"),
        rightHeader = $("div[class='HeaderRightSide']"),
        loginRegisterForm = $("section[class='login-register-outer']");

}
