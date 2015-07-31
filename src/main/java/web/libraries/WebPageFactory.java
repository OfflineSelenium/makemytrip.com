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
    private LoginPage logInPage;
    @Autowired
    private SearchPage searchPage;
    @Autowired
    private SearchResultsPage searchResultsPage;

    public void checkWeAreOnTheRightPage(String titlePage) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (!wait.until(ExpectedConditions.titleContains(titlePage)))
            throw new IllegalStateException(String.format("This is not the %s page.", titlePage));
    }

    public LoginPage loadLogInPage() {
        PageFactory.initElements(driver, logInPage);
        logInPage.load();
        return logInPage;
    }

    public LoginPage getLogInPage() {
        PageFactory.initElements(driver, logInPage);
        return logInPage;
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
