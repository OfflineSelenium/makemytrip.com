package makemytrip.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    @FindBy(id = "login_dropOpenItem")
    private WebElement ddlLogin;

    @FindBy(id = "username")
    private WebElement txtEmail;

    @FindBy(id = "password_text")
    private WebElement txtPassword;

    @FindBy(id = "login_btn")
    private WebElement btnContinue;

    public void load() {
        loadPage("/");
    }

    public void isLoaded() {
        webPageFactory.checkWeAreOnTheRightPage("MakeMyTrip, India's No 1 Travel Site | Book Flights, Hotels, Holiday Packages & Bus Tickets");
    }

    public LoginPage clickLoginDropDown() {
        ddlLogin.click();
        return this;
    }

    public LoginPage enterEmail(String email) {
        txtEmail.clear();
        txtEmail.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }

    public LoginPage clickContinueSuccess() {
        btnContinue.click();
        return this;
    }

    public LoginPage clickContinueFailure() {
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

    public LoginPage clickLoginDropOpenItem() {
        ddlLogin.click();
        return this;
    }

    public boolean shouldSeeTextEmailError(String text) {
        return webElementFinder.findElementByLocatorCssSelector("#login_form_0 #f_oldemail_emailerror .chf_errortext").getText().equals(text);
    }

    public boolean shouldSeeTextPasswordError(String text) {
        return webElementFinder.findElementByLocatorCssSelector("#unregistered_email_span #errormsg_password").getText().equals(text);
    }

    /*
     * When opening the page, a popup will be displayed as a <iframe> element tag.
     * Check the element of the <iframe> tag as displayed or not usual.
     */
    public boolean isPopupMegaHolidaySaleDisplayed() {
        WebElement element = webElementFinder.findElementByLocatorID("webklipper-publisher-widget-container-notification-frame");
        return element.isDisplayed();
    }

    /*
     * NOTICE: This is for working with switching the driver to an iframe
     * After using the iframe with finding its elements, then switch back to the current page.
     */
    public LoginPage closePopupMegaHolidaySale() {
        WebElement iframe = webElementFinder.findElementByLocatorID("webklipper-publisher-widget-container-notification-frame");
        WebElement element = webPageFactory.getDriver().switchTo().frame(iframe).findElement(By.id("webklipper-publisher-widget-container-notification-close-div"));
        element.click();
        webPageFactory.getDriver().switchTo().defaultContent();
        return this;
    }

}
