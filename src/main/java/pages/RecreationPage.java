package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RecreationPage {
    public SelenideElement sidebar = $("#sidebar"),
        section = $("section"),
        bannerClose = $("div[class='banner-close-footer banner-close-all']"),
        banner = $("div[class='bottom-header-content']"),
        parentElement = $(".category-filter-desk");
//        items = $("#partialid");

}
