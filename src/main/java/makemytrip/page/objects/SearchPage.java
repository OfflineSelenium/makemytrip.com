package makemytrip.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class SearchPage extends BasePage {

    @FindBy(id = "from_city_data_box")
    private WebElement txtSearch;

    @FindBy(xpath = "//span[contains(@class,'night_number')]")
    private WebElement cmbNight;

    @FindBy(xpath = "//div[contains(@class,'first_room_div')]//div[contains(@class,'segmented_controls')]//a[2]")
    private WebElement Adult;

    @FindBy(xpath = "//div[contains(@class,'col-sm-3')]//div[contains(@class,'segmented_controls')]//a[2]")
    private WebElement Children;

    @FindBy(id = "hotels_submit")
    private WebElement btnClickSearch;

    public void load() {
        loadPage("/hotels/");
    }

    public SearchPage enterSearchKeyword(String text) {
        txtSearch.clear();
        txtSearch.sendKeys(text);

        //waiting element displayed
        String xpath = "//div[contains(@class,'modify_ddn_fields')]//span[contains(@class,'tt-dropdown-menu')]";
        webElementFinder.findElementByLocatorXPath(xpath).isDisplayed();

        return this;
    }

    public SearchPage chooseNight(String night) {
        cmbNight.sendKeys(night);
        return this;
    }

    public SearchPage chooseAdult() {
        Adult.click();
        return this;
    }

    public SearchPage chooseChildren() {
        Children.click();
        return this;
    }

    public SearchResultsPage clickSearch() {
        btnClickSearch.click();
        return webPageFactory.loadSearchResultsPage();
    }
}
