package makemytrip.page.tests;

import makemytrip.page.objects.SearchPage;
import makemytrip.page.objects.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchPageTest extends BaseTest {

    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        searchPage = webPageFactory.loadSearchPage();
    }

    @BeforeMethod
    public void beforeMethod() {
        searchPage.load();
    }

    @Test(description = "Search - verify Search Results Domestic Hotels Found")
    public void verifySearchResultsHotelsFound() {
        searchResultsPage = searchPage.clickTabHotels()
                .enterSearchKeyword("London, United Kingdom")
                .chooseNight("5")
//                .chooseCheckInDate("18/09/2015")
                .chooseAdult()
                .chooseChildren()
                .clickSearch();

        Assert.assertTrue(searchResultsPage.shouldSeeTextTitle("Hotels in London"));
        Assert.assertTrue(searchResultsPage.shouldSeeNightSearchResult("5"));
        Assert.assertTrue(searchResultsPage.shouldSeeRoomSearchResult("1 "));
        Assert.assertTrue(searchResultsPage.shouldSeePeopleSearchResult("3"));
    }

    @Test(description = "Search - verify Search Results International Hotels Found")
    public void verifySearchResultsInternationalHotelsFound() {
        searchResultsPage = searchPage.clickTabHotels()
                .clickTabInternational()
                .enterSearchKeyword("Ho Chi Minh City, Vietnam")
                .clickSearch()
                .moveSlider();

        Assert.assertTrue(searchResultsPage.shouldSeePriceSearchResult());
    }

    @Test(description = "Search - verify Select Hotel Location")
    public void verifyCheckHotealLocation() {
        searchResultsPage = searchPage.clickTabHotels()
                .clickTabInternational()
                .enterSearchKeyword("Ho Chi Minh City, Vietnam")
                .clickSearch()
                .checkedHotelLocation("District 5");

        Assert.assertTrue(searchResultsPage.shouldSeeHotelLocationSearch());
    }
}