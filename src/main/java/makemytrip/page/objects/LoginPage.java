package makemytrip.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    @FindBy(id = "ctl00_ctl00_MainContent_ContentMain_RewardLogin1_txtEmail")
    private WebElement txtEmail;

    @FindBy(id = "ctl00_ctl00_MainContent_ContentMain_RewardLogin1_txtPassword")
    private WebElement txtPassword;

    @FindBy(id = "ctl00_ctl00_MainContent_ContentMain_RewardLogin1_btnSignIn")
    private WebElement btnSignIn;

    private WebElement getEmailValidation() {
        return webElementFinder.findElementByLocatorID("ctl00_ctl00_MainContent_ContentMain_RewardLogin1_rfvEmail");
    }

    private WebElement getPasswordValidation() {
        return webElementFinder.findElementByLocatorID("ctl00_ctl00_MainContent_ContentMain_RewardLogin1_rfvPassword");
    }

    public void load() {
        loadPage("/");
    }

    public void isLoaded() {
        webPageFactory.checkWeAreOnTheRightPage("Sign In");
    }

    public LoginPage enterEmail(String email) {
        txtEmail.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }

    public LoginPage clickSignInFailed() {
        btnSignIn.click();
        return this;
    }

    public String signInNotificationText() {
        return findElementByLocator("notification_sign_in").getText();
    }

    public String emailValidationText() {
        return getEmailValidation().getText();
    }

    public String passwordValidationText() {
        return getPasswordValidation().getText();
    }

    public String emailValidationTextColor() {
        return getEmailValidation().getCssValue("color");
    }

    public String passwordValidationTextColor() {
        return getPasswordValidation().getCssValue("color");
    }

}
