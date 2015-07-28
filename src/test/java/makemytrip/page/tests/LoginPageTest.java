package makemytrip.page.tests;

import makemytrip.page.objects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        loginPage = webPageFactory.loadLogInPage();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        refreshPage();
    }


    /**
     * ******************************************************************************************
     * PERFORMS ALL TEST CASES																	*
     * ******************************************************************************************
     */
    @Test(description = "Verify logging in passed")
    public void verifyLoginPassed() {
        loginPage.clickLoginDropDown()
                 .enterUsername("test.secude@gmail.com")
                 .enterPassword("Admin-123")
                 .clickContinueSuccessed();

        Assert.assertTrue(loginPage.shouldSeeUsername("Test"));

        loginPage.clickLoginDropDown()
                 .clickLogoutLink();
    }

}
