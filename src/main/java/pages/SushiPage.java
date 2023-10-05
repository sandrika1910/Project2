package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SushiPage {
    public SelenideElement container = $("div[class='items'][id='partialid']"),
        sortProductsElement = $("#sort"),
        firstProduct = $("div[class='special-offer-img-container']");
}
