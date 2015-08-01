package web.libraries;

import makemytrip.page.objects.SearchPage;
import makemytrip.page.objects.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import makemytrip.page.objects.LoginPage;

@Component
public class WebPageFactory {

    @Autowired
    private WebDriver driver;

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private SearchPage searchPage;

    @Autowired
    private SearchResultsPage searchResultsPage;

    public void checkWeAreOnTheRightPage(String titlePage) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (!wait.until(ExpectedConditions.titleContains(titlePage)))
            throw new IllegalStateException(String.format("This is not the %s page.", titlePage));
    }

    public LoginPage loadLoginPage() {
        PageFactory.initElements(driver, loginPage);
        loginPage.load();
        if (loginPage.isPopupMegaHolidaySaleDisplayed())
            loginPage.closePopupMegaHolidaySale();
        return loginPage;
    }

    public LoginPage getLoginPage() {
        PageFactory.initElements(driver, loginPage);
        return loginPage;
    }

    public SearchPage loadSearchPage(){
        PageFactory.initElements(driver, searchPage);
        searchPage.load();
        return searchPage;
    }

    public SearchResultsPage loadSearchResultsPage(){
        PageFactory.initElements(driver, searchResultsPage);
        return searchResultsPage;
    }

    public WebDriver getDriver() {
        return driver;
    }

}
