package steps;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import data.CustomListener;
import data.SqlManipulation;
import data.User;
import io.qameta.allure.Step;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;

import java.util.Map;

import static com.codeborne.selenide.Configuration.assertionMode;

@Listeners({CustomListener.class})
public class RegisterPageSteps {
    RegisterPage registerPage = new RegisterPage();
    User user = new User();

    @Step("click on 'რეგისტრაცია'")
    public RegisterPageSteps clickOnRegisterButton() {
        registerPage.registerButton.hover().click();
        registerPage.registerButton.shouldHave(Condition.attribute("aria-selected","true"));
        return this;
    }

    @Step("click on 'ფიზიკური პირი'")
    public RegisterPageSteps clickOnPhisicalPerson() {
        registerPage.phisicalPersonButton.hover().click();
        registerPage.phisicalPersonButton.shouldHave(Condition.attribute("aria-selected", "true"));
        return this;
    }

    @Step("Step -> get user data from database")
    public RegisterPageSteps setUserToUserClassFromDb() {
        Map<String, String> map = SqlManipulation.returnLastRow();
        user.setId(map.get("id"));
        user.setFirstName(map.get("firstName"));
        user.setLastName(map.get("lastName"));
        user.setEmail(map.get("email"));
        user.setPhone(map.get("phone"));
        user.setDateOfBirth(map.get("dateOfBirth"));
        user.setPassword(map.get("password"));
        return this;
    }

    @Step("Step -> fill 'სახელი'")
    public RegisterPageSteps fillFirstName() {
        registerPage.loginRegisterForm.$("#pFirstName").sendKeys(user.getFirstName());
        return this;
    }

    @Step("Step -> fill 'გვარი'")
    public RegisterPageSteps fillLastName() {
        registerPage.loginRegisterForm.$("#pLastName").sendKeys(user.getLastName());
        return this;
    }

    @Step("Step -> fill 'ემეილი'")
    public RegisterPageSteps fillEmail() {
        registerPage.loginRegisterForm.$("#pEmail").sendKeys(user.getEmail());
        return this;
    }

    @Step("Step -> fill 'მობილური'")
    public RegisterPageSteps fillPhone() {
        registerPage.loginRegisterForm.$("#pPhone").sendKeys(user.getPhone());
        return this;
    }

    @Step("Step -> fill 'დაბადების თარიღი'")
    public RegisterPageSteps fillDate() {
        SelenideElement element = registerPage.loginRegisterForm.$("#pDateBirth");
        String dateOfBirth = user.getDateOfBirth().replaceAll("-","");
        element.sendKeys(dateOfBirth);
//        Selenide.executeJavaScript("arguments[0].valueAsDate= new Date(arguments[1])",element,user.getDateOfBirth());
        return this;
    }

    @Step("Step -> fill 'პაროლი'")
    public RegisterPageSteps fillPassword() {
        registerPage.loginRegisterForm.$("#pPassword").sendKeys(user.getPassword());
        return this;
    }

    @Step("Step -> fill 'გაიმეორეთ პაროლი'")
    public RegisterPageSteps fillConfirmPassword() {
        registerPage.loginRegisterForm.$("#pConfirmPassword").sendKeys("Dba1242452142@");
        return this;
    }

    @Step("Step -> check terms")
    public RegisterPageSteps checkTerms() {
        SelenideElement element = registerPage.loginRegisterForm.$("#pIsAgreeTerns");
        element.click();
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true)", element);
        return this;
    }

    @Step("Step -> click on 'რეგისტრაცია'")
    public RegisterPageSteps clickOnSubmit() {
        SelenideElement element = registerPage.loginRegisterForm.$("a[onclick='checkPhysicalFormSubmit()']");
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true)", element);
        element.click();
        return this;
    }

    @Step("Step -> check message 'გთხოვთ აირჩიოთ სქესი!' has appeared")
    public RegisterPageSteps checkMessage() {
        String text = "გთხოვთ აირჩიოთ სქესი!";
        SelenideElement message = registerPage.loginRegisterForm.$("#physicalInfoMassage");
        message.shouldHave(Condition.text(text));
//        softAssert.assertEquals(text,message.getText());
        assert text.equals(message.getText());
//        softAssert.assertAll();
        return this;
    }
}
