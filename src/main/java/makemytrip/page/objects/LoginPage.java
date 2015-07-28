package makemytrip.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    @FindBy(id = "ssologinlink")
    private WebElement ddlLogin;

    @FindBy(id = "username")
    private WebElement txtUsername;

    @FindBy(id = "password_text")
    private WebElement txtPassword;

    @FindBy(id = "login_btn")
    private WebElement btnContinue;

    public void load() {
        loadPage("/");
    }

    public void isLoaded() {
//        webPageFactory.checkWeAreOnTheRightPage("Sign In");
    }

    public LoginPage clickLoginDropDown() {
        ddlLogin.click();
        return this;
    }

    public LoginPage enterUsername(String username) {
        txtUsername.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }

    public LoginPage clickContinueSuccessed() {
        btnContinue.click();
        return this;
    }

    public boolean shouldSeeUsername(String username) {
        WebElement element = webElementFinder.findElementByLocatorXPath("//span[@id='ssologinlink']/strong[text()!='My Account']");
        return element.getText().equals(username);
    }

    public LoginPage clickLogoutLink() {
        WebElement lnkLogout = webElementFinder.findElementByLocatorXPath("//div[@id='afterlogin_div']//ul[@class='chf_account_links']//input");
        lnkLogout.click();
        return this;
    }

}
