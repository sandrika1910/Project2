package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {
    public SelenideElement loginRegisterForm = $("section[class='login-register-outer']"),
            registerButton = $x("//*[@id=\"toogletabs\"]/div[1]/div/ul/li[2]"),
            phisicalPersonButton = $x("//*[@id=\"registertabs\"]/ul/li");
}
