package makemytrip.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//div[4]/p[2]/span")
    private WebElement searchResultNighht;

    @FindBy(xpath = "//div[5]/p[2]/span")
    private WebElement searchResultRooms;

    @FindBy(xpath = "//div[6]/p[2]/span")
    private WebElement searchResultPeople;

    public boolean shuoldSeeTextTitle(String text) {
        return driver.getTitle().contains(text);
    }

    public boolean shouldSeeNightSearchResult(String text) {
        return searchResultNighht.getText().equals(text);
    }

    public boolean shouldSeeRoomSearchResult(String text) {
        return searchResultRooms.getText().contains(text);
    }

    public boolean shouldSeePeopleSearchResult(String text) {
        return searchResultPeople.getText().equals(text);
    }

}
