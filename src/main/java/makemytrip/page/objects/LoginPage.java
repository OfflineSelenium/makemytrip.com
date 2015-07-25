package makemytrip.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    @FindBy(id = "ssologinlink")
    private WebElement menuSignin;

    @FindBy(id = "username")
    private WebElement txtUsername;

    @FindBy(id = "password_text")
    private WebElement txtPassword;

    @FindBy(id = "login_btn")
    private WebElement btnContinue;

    public void load() {
        loadPage("/");
    }

    public LoginPage clickSigninMenu() {
        menuSignin.click();
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
        WebElement element = webElementFinder.findElementByLocatorXPath("//span[@id='ssologinlink']/strong");
        return element.getText().equals(username);
    }
}
