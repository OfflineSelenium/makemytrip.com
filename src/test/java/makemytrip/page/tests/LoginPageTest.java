package makemytrip.page.tests;

import makemytrip.page.objects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.libraries.Common;

public class LoginPageTest extends BaseTest {

    private LoginPage loginPage;
    private String EMAIL_VALID;
    private String EMAIL_INVALID;
    private String PASSWORD_VALID;
    private String PASSWORD_INVALID;
    private String MESSAGE_ERROR_INVALID_EMAIL;
    private String MESSAGE_ERROR_INVALID_PASSWORD;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        loginPage = webPageFactory.loadLoginPage();

        EMAIL_VALID = "testingmakemytrip@yahoo.com";
        EMAIL_INVALID = "abc.mail.com";
        PASSWORD_VALID = "123456abc";
        PASSWORD_INVALID = "123456ABCD";
        MESSAGE_ERROR_INVALID_EMAIL = "Please enter a valid Email Address.";
        MESSAGE_ERROR_INVALID_PASSWORD = "EMail-ID not registered. Please try a different ID.";
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
                 .enterEmail("test.secude@gmail.com")
                 .enterPassword("Admin-123")
                 .clickContinueSuccess();

        Assert.assertTrue(loginPage.shouldSeeUsername("Test"));

        loginPage.clickLoginDropDown()
                 .clickLogoutLink();
        Common.sleep(5000);
    }

    @Test(description = "Verify login failed with invalid email")
    public void verifyLogInFailedWithInvalidEmail() {
        loginPage.clickLoginDropOpenItem()
                 .enterEmail(EMAIL_INVALID)
                 .enterPassword(PASSWORD_VALID)
                 .clickContinueFailure();

        Assert.assertTrue(loginPage.shouldSeeTextEmailError(MESSAGE_ERROR_INVALID_EMAIL));
    }

    @Test(description = "Verify login failed with invalid password")
    public void verifyLogInFailedWithInvalidPassword() {
        loginPage.clickLoginDropOpenItem()
                 .enterEmail(EMAIL_VALID)
                 .enterPassword(PASSWORD_INVALID)
                 .clickContinueFailure();

        Assert.assertTrue(loginPage.shouldSeeTextPasswordError(MESSAGE_ERROR_INVALID_PASSWORD));
    }

}
