import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.SoftAsserts;
import data.CustomListener;
import data.TestConfigClass;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import steps.*;

@Epic("Test 'www.swoop.ge' using selenide and allure framework")
@Feature("JDK20 available")
@Listeners({CustomListener.class, SoftAsserts.class})
public class Main extends TestConfigClass {
    SoftAssert softAssert = new SoftAssert();
    HomePageSteps hps = new HomePageSteps();
    RecreationPageSteps rps = new RecreationPageSteps(softAssert);
    SushiPageSteps sushiPageSteps = new SushiPageSteps(softAssert);
    FirstProductPageSteps fpp = new FirstProductPageSteps(softAssert);
    RegisterPageSteps registerPageSteps = new RegisterPageSteps(softAssert);
    @BeforeMethod(groups = {"Regression1", "Regression2"})
    public void setUp() {
        super.setUp();
    }

    @Test(groups={"Regression1"}, description = "go to 'კატეგორიები' and set price range 200 to 230")
    @Story("Page 'დასვენება' 's products  prices check")
    @Description("go to 'დასვენება' and set price range 200 to 230 and check returned products prices")
    public void test1() {
        Selenide.open("https://www.swoop.ge/");
        hps.closeCookies().goToRecreationPage();
        rps.scrollDown().closeBanner().scrollDown2().setPriceRange().waitForLoadPage().checkPriceRangeSetResult();
        softAssert.assertAll();
    }

    @Test(groups={"Regression1"}, description = "try to add products to favorites on 'სუში' page")
    @Story("Add product to favorites")
    @Description("Click on 'კატეგორიები', hover on 'ჭამა', click on 'სუში', try to add first item to favorites, check if login and register page appear, check voucher not sold")
    public void test2(){
        Selenide.open("https://www.swoop.ge/");
        hps.clickOnCategories().hoverOnEat().clickOnSushi();
        sushiPageSteps.addToFavorites().checkIfLoginFormAppear().checkVoucher();
        softAssert.assertAll();
    }

    @Test(groups={"Regression2"}, description = "Check if sorting by descending price work correctly on 'სუში' page")
    @Story("Sorting 'სუში' 's products by prices desc order")
    @Story("Go to 'სუში' page, sort items by 'ფასით კლებადი' and check if first product's price is greater than second product's price")
    public void test3() {
        Selenide.open("https://www.swoop.ge/");
        hps.clickOnCategories().hoverOnEat().clickOnSushi();
        sushiPageSteps.clickOnSort().waitForSort().checkFirstAndSecondProductsPrices();
        softAssert.assertAll();
    }

    @Test(groups={"Regression2"}, description = "Test if button 'გაზიარება' work on 'სუში''s page's first product")
    @Story("Check 'გაზიარება' button")
    @Description("Go to 'სუში' page, go to first product, click on 'გაზაირება' and check if new window with facebook login page appear")
    public void test4() {
        Selenide.open("https://www.swoop.ge/");
        hps.clickOnCategories().hoverOnEat().clickOnSushi();
        sushiPageSteps.clickFirstProduct();
        fpp.clickOnShare().checkWindow();
        softAssert.assertAll();
    }

    @Test(groups={"Regression2"}, description = "Try to register new account with data from database")
    @Story("Try to register new account with data from database")
    @Description("Generate data for new user in database, get data from database and register new account based on this data without filling gender")
    public void testDatabaseAndRegisterForm() {
        data.SqlManipulation.doInsert();
        Selenide.open("https://www.swoop.ge/");
        hps.closeCookies().clickOnLogin();
        registerPageSteps.clickOnRegisterButton()
                .clickOnPhisicalPerson()
                .setUserToUserClassFromDb()
                .fillFirstName()
                .fillLastName()
                .fillEmail()
                .fillPhone()
                .fillDate()
                .fillPassword()
                .fillConfirmPassword()
                .checkTerms()
                .clickOnSubmit()
                .checkMessage();
        softAssert.assertAll();
    }
    @AfterMethod(groups = {"Regression1", "Regression2"})
    public void tearDown() {
        super.tearDown();
    }
}
