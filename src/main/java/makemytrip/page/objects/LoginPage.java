package makemytrip.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

@Component
public class LoginPage extends BasePage {

    @FindBy(id = "login_dropOpenItem")
    private WebElement btnDropOpenItem;

    @FindBy(id = "username")
    private WebElement txtEmail;

    @FindBy(id = "password_text")
    private WebElement txtPassword;

    @FindBy(id = "login_btn")
    private WebElement btnContinue;

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

    public LoginPage clickContinueFailure() {
        btnContinue.click();
        return this;
    }

    public LoginPage clickLoginDropOpenItem()
    {
        btnDropOpenItem.click();
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

    public boolean shouldSeeTextEmailError(String text){
        return findElementByLocatorCssSelector("#login_form_0 #f_oldemail_emailerror .chf_errortext").getText().equals(text);
    }

    public boolean shouldSeeTextPasswordError(String text){
        return findElementByLocatorCssSelector("#unregistered_email_span #errormsg_password").getText().equals(text);
    }
}
