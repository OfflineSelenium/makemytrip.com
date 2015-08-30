package makemytrip.page.tests;

import makemytrip.page.objects.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import web.libraries.Common;

import java.text.ParseException;

public class HotelsPageTest extends BaseTest {

    private HotelsPage hotels;
    public static String checkIn = "10/09/2015";
    public static String checkOut = "12/30/2015";

    public static int intCheckInDay = Common.splitDate(checkIn)[1];
    public static int intCheckInMonth = Common.splitDate(checkIn)[0];
    public static int intCheckInYear = Common.splitDate(checkIn)[2];

    public static int intCheckOutDay = Common.splitDate(checkOut)[1];
    public static int intCheckOutMonth = Common.splitDate(checkOut)[0];
    public static int intCheckOutYear = Common.splitDate(checkOut)[2];

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        hotels = webPageFactory.loadHotelsPage();
    }

//    @BeforeMethod(alwaysRun = true)
//    public void beforeMethod() {
//        refreshPage();
//    }

    /**
     * ******************************************************************************************
     * PERFORMS ALL TEST CASES																	*
     * ******************************************************************************************
     */
    @Test(description = "Verify Night date = Check Out - Check In")
    public void verifyNightDay() throws ParseException {
        hotels = hotels.selectCheckIn(checkIn)
                .selectCheckOut(checkIn, checkOut);
        Assert.assertEquals(hotels.countNight(checkIn, checkOut), hotels.getNumberExpectedNight());
    }
}
