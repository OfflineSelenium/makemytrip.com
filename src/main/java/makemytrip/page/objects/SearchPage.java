package makemytrip.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SearchPage extends BasePage {

    @FindBy(xpath = "//li[3]//span[contains(@class, 'tab_txt')]")
    private WebElement tabHotel;

    @FindBy(id = "roundTripTab")
    private WebElement tabInternational;

    @FindBy(id = "from_city_data_box")
    private WebElement txtSearch;

    @FindBy(xpath = "//div[contains(@class,'first_room_div')]//div[contains(@class,'segmented_controls')]//a[2]")
    private WebElement Adult;

    @FindBy(xpath = "//div[contains(@class,'col-sm-3')]//div[contains(@class,'segmented_controls')]//a[2]")
    private WebElement Children;

    @FindBy(id = "hotels_submit")
    private WebElement btnClickSearch;

    public void load() {
        loadPage("/");
    }

    public SearchPage chooseNight(String night) {
        String xpathdp = "//div[@id='nightAnchor']/div";
        WebElement dplistClick = webElementFinder.findElementByLocatorXPath(xpathdp);
        dplistClick.click();

        String xpath = "//ul[contains(@class,'nights-dropdown-menu')]/li";
        List<WebElement> elementList = webElementFinder.findElementsByLocatorXPath(xpath);
        int dpListCount = elementList.size();

        for (int i = 1; i < dpListCount; i++) {
            if (elementList.get(i).getText().contentEquals(night))
                elementList.get(i).click();
        }
        return this;
    }

    public SearchPage clickTabHotels() {
        tabHotel.click();
        webPageFactory.checkWeAreOnTheRightPage("Online Hotel Booking for Cheap, Budget & Luxury Hotels in India | MakeMyTrip.com");
        return this;
    }

    public SearchPage clickTabInternational() {
        tabInternational.click();
        webPageFactory.checkWeAreOnTheRightPage("Book International Hotels Online | Get Discounts & Offers for Cheap, Budget & Luxury Hotels Worldwide | MakeMyTrip");
        return this;
    }

    public SearchPage enterSearchKeyword(String text) {
        txtSearch.clear();
        txtSearch.sendKeys(text);

        //waiting element displayed
        String xpath = "//div[contains(@class,'modify_ddn_fields')]//span[contains(@class,'tt-dropdown-menu')]";
        webElementFinder.findElementByLocatorXPath(xpath).isDisplayed();

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

    public SearchPage chooseCheckInDate(String date) {
        webElementFinder.findElementByLocatorID("start_date_sec").click();
        chooseDatetime(date);
        return this;
    }

    public SearchPage chooseCheckOutDate(String date) {
        webElementFinder.findElementByLocatorID("return_date_sec").click();
        chooseDatetime(date);
        return this;
    }

    //---------------------------Date Picker-----------------------------
    public void chooseDatetime(String date) {
        List<String> list = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

        String calMonth = null;
        String calYear = null;
        boolean dateNotFound;

        dateNotFound = true;

        String dateTime = date;
        String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");

        int expMonth = Integer.parseInt(date_dd_MM_yyyy[1]);
        int expYear = Integer.parseInt(date_dd_MM_yyyy[2]);
        int expDate = Integer.parseInt(date_dd_MM_yyyy[0]);

        while (dateNotFound) {
            calMonth = webElementFinder.findElementByClassName("ui-datepicker-month").getText();
            calYear = webElementFinder.findElementByClassName("ui-datepicker-year").getText();

            if (list.indexOf(calMonth) + 1 == expMonth && (expYear == Integer.parseInt(calYear))) {
                selectDate(expDate);
                dateNotFound = false;
            } else if (list.indexOf(calMonth) + 1 < expMonth && (expYear == Integer.parseInt(calYear)) || expYear > Integer.parseInt(calYear)) {
                webElementFinder.findElementByLocatorXPath("//a[contains(@class, 'ui-datepicker-next')]").click();
            } else if (list.indexOf(calMonth) + 1 > expMonth && (expYear == Integer.parseInt(calYear)) || expYear < Integer.parseInt(calYear)) {
                webElementFinder.findElementByLocatorXPath("//a[contains(@class, 'ui-datepicker-next')]").click();
            }
        }
    }

    public void selectDate(int date) {
        List<WebElement> rows;
        List<WebElement> columns;
        WebElement dateWidget;
        String dateStr = Integer.toString(date);
        dateWidget = webElementFinder.findElementByLocatorID("ui-datepicker-div");
        rows = dateWidget.findElements(By.tagName("tr"));
        columns = dateWidget.findElements(By.tagName("td"));

        for (WebElement cell : columns) {
            //Selects Date
            if (cell.getText().equals(dateStr)) {
                cell.findElement(By.linkText(dateStr)).click();
                break;
            }
        }
    }
    //--------------------End Date Picker-------------------------------------------
}