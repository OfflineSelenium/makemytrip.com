package makemytrip.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import web.libraries.Common;

import java.util.List;

@Component
public class SearchResultsPage extends BasePage {

    public boolean shouldSeeTextTitle(String text) {
        return driver.getTitle().contains(text);
    }

    public boolean shouldSeeNightSearchResult(String text) {
        String xpath = "//div[4]/p[2]/span";
        WebElement searchResultNighht = webElementFinder.findElementByLocatorXPath(xpath);

        return searchResultNighht.getText().equals(text);
    }

    public boolean shouldSeeRoomSearchResult(String text) {
        String xpath = "//div[5]/p[2]/span";
        WebElement searchResultRooms = webElementFinder.findElementByLocatorXPath(xpath);

        return searchResultRooms.getText().contains(text);
    }

    public boolean shouldSeePeopleSearchResult(String text) {
        String xpath = "//div[6]/p[2]/span";
        WebElement searchResultPeople = webElementFinder.findElementByLocatorXPath(xpath);

        return searchResultPeople.getText().equals(text);
    }

    public SearchResultsPage moveSlider() {
        String xpath = "//div[contains(@class,'slider-handle')][1]";
        WebElement dragFrom = webElementFinder.findElementByLocatorXPath(xpath);

        new Actions(driver).clickAndHold(dragFrom).moveByOffset(130, 0).release().perform();
        Common.sleep(10000);
        return this;
    }

    public boolean shouldSeePriceSearchResult() {
        boolean result = true;

        //Get list price hotels
        String xpath = "//p[contains(@class,'actual_price text-right ng-binding')]";
        List<WebElement> listPrice = webElementFinder.findElementsByLocatorXPath(xpath);
        int listHotels = listPrice.size();

        //Get min Price
        String xpath_minPrice = "//span[contains(@class,'price_text')][1]/span[text()!='Rs.']";
        WebElement min_Price = webElementFinder.findElementByLocatorXPath(xpath_minPrice);
        String mnp = min_Price.getText();
        int minPrice = Integer.parseInt(mnp);

        //Get max Price
        String xpath_maxPrice = "//span[contains(@class,'price_text')][2]/span[text()!='Rs.']";
        WebElement max_Price = webElementFinder.findElementByLocatorXPath(xpath_maxPrice);
        String mxp = max_Price.getText();
        int maxPrice = Integer.parseInt(mxp);

        for (int i = 0; i < listHotels; i++) {
            String lp = listPrice.get(i).getText();
            String list_price[] = lp.split("Rs. ");
            int price = Integer.parseInt(list_price[1]);

            if (price > maxPrice || price < minPrice) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }

    public SearchResultsPage checkedHotelLocation(String hotel_location) {
        WebElement location = webElementFinder.findElementByLocatorXPath("//*[@id='ARFCDistrict']/span[text()='" + hotel_location + "']");
        location.getText();
        location.click();
        return this;
    }

    public boolean shouldSeeHotelLocationSearch() {
        boolean result = true;

        //Get number hotel on location
        String xpath = "//div[contains(@class,'active')]//span[@class='pull-left locatn_number ng-binding']";
        WebElement location = webElementFinder.findElementByLocatorXPath(xpath);
        String number = location.getText();
        int number_location = Integer.parseInt(number);

        //Get list hotels
        String list_xpath = "//p[contains(@class,'actual_price text-right ng-binding')]";
        List<WebElement> listPrice = webElementFinder.findElementsByLocatorXPath(list_xpath);
        int listHotels = listPrice.size();

        if (number_location == listHotels) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

}
