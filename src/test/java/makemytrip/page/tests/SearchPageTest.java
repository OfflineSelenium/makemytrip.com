package makemytrip.page.tests;

import makemytrip.page.objects.SearchPage;
import makemytrip.page.objects.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchPageTest extends BaseTest {

    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        searchPage = webPageFactory.loadSearchPage();
    }

    @Test(description = "verify Search Results Found")
    public void verifySearchResultsFound() {
        searchResultsPage = searchPage.enterSearchKeyword("London, United Kingdom")
                                      .chooseNight("2")
                                      .chooseAdult()
                                      .chooseChildren()
                                      .clickSearch();

        Assert.assertTrue(searchResultsPage.shuoldSeeTextTitle("Hotels in London"));
        Assert.assertTrue(searchResultsPage.shouldSeeNightSearchResult("2"));
        Assert.assertTrue(searchResultsPage.shouldSeeRoomSearchResult("1 "));
        Assert.assertTrue(searchResultsPage.shouldSeePeopleSearchResult("3"));
    }
}