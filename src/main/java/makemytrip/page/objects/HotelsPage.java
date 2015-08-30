package makemytrip.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import web.libraries.Common;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Component
public class HotelsPage extends BasePage {

    public void load() {
        loadPage("/hotels");
    }

    public void isLoaded() {
        webPageFactory.checkWeAreOnTheRightPage("Online Hotel Booking for Cheap, Budget & Luxury Hotels in India | MakeMyTrip.com");
    }

    @FindBy(xpath = "//span[@class='ui-datepicker-month']")
    List<WebElement> presentMonths;

    @FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-first']//td[@data-handler='selectDay']")
    List<WebElement> dateOfFirstMonth;

    @FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-last']//td[@data-handler='selectDay']")
    List<WebElement> dateOfLastMonth;

    @FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-first']//div[@class='ui-datepicker-title']/span[1]")
    WebElement monthOfFirstGroup;

    @FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-last']//div[@class='ui-datepicker-title']/span[1]")
    WebElement monthOfLastGroup;

    @FindBy(xpath = "//a[@id='start_date_sec']/span[1]/span[2]")
    WebElement dateCheckIn;

    @FindBy(xpath = "//a[@id='return_date_sec']/span[1]/span[2]")
    WebElement dateCheckOut;

    @FindBy(xpath = "//div[@id='nightAnchor']/div/p[2]/span[@class='night_number']")
    WebElement numbNight;

    @FindBy(xpath = "//span[@class='ui-icon ui-icon-circle-triangle-w']")
    WebElement calendarPrev;

    @FindBy(xpath = "//span[@class='ui-icon ui-icon-circle-triangle-e']")
    WebElement calendarNext;

    public HotelsPage selectCheckIn(String checkInDate) throws ParseException {
        dateCheckIn.click();
        for(int m = 0; m < 10; m ++) {
            int firstMonth = Common.convertMonthString2Int(monthOfFirstGroup.getText());
            int lastMonth = Common.convertMonthString2Int(monthOfLastGroup.getText());
            String strCheckInDate = Common.verifyCheckInDate(checkInDate);
            int date = Common.splitDate(strCheckInDate)[1];
            int checkInMonth = Common.splitDate(checkInDate)[0];
            if (checkInMonth == firstMonth) {
                for (int i = 0; i < dateOfFirstMonth.size(); i++)
                    if (Integer.parseInt(dateOfFirstMonth.get(i).getText()) == date) {
                        dateOfFirstMonth.get(i).click();
                        return this;
                    }
            } else if (checkInMonth == lastMonth) {
                for (int i = 0; i < dateOfLastMonth.size(); i++)
                    if (Integer.parseInt(dateOfLastMonth.get(i).getText()) == date) {
                        dateOfLastMonth.get(i).click();
                        return this;
                    }

            }
            if(checkInMonth > lastMonth){
                calendarNext.click();
            }
            if(checkInMonth < firstMonth){
                System.out.println("CHECKIN DATE IS INVALID");
                return this;
            }
        }
        return this;
    }

    public HotelsPage selectCheckOut(String checkInDate, String checkOutDate) throws ParseException {
        dateCheckOut.click();
        for(int m = 0; m < 10; m ++){
            int firstMonth = Common.convertMonthString2Int(monthOfFirstGroup.getText());
            int lastMonth = Common.convertMonthString2Int(monthOfLastGroup.getText());
            int date = Common.splitDate(checkOutDate)[1];
            int checkOutMonth = Common.splitDate(checkOutDate)[0];
            if (checkOutMonth == firstMonth) {
                if (Common.verifyCheckOutDate(checkInDate, checkOutDate)) {
                    for (int i = 0; i < dateOfFirstMonth.size(); i++)
                        if (Integer.parseInt(dateOfFirstMonth.get(i).getText()) == date) {
                            dateOfFirstMonth.get(i).click();
                            return this;
                        }
                }
            }
            if (checkOutMonth == lastMonth) {
                if (Common.verifyCheckOutDate(checkInDate, checkOutDate)) {
                    for (int i = 0; i < dateOfLastMonth.size(); i++)
                        if (Integer.parseInt(dateOfLastMonth.get(i).getText()) == date) {
                            dateOfLastMonth.get(i).click();
                            return this;
                        }
                }
            }
            if(checkOutMonth > lastMonth){
                calendarNext.click();
            }
            if(checkOutMonth < firstMonth){
                System.out.println("CHECKOUT DATE IS INVALID");
                return this;
            }

        }
        return this;
    }

    public String countNight(String checkInDay, String checkOutDay) throws ParseException {
        String strCheckInDate = Common.verifyCheckInDate(checkInDay);
        Date checkIn = Common.string2Date(strCheckInDate);
        Date checkOut = Common.string2Date(checkOutDay);
        int days = (int) ((checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));

        if (days > 30)
            return "30+";
        else
            return String.valueOf(days);
    }

    public String getNumberExpectedNight() {
        return (numbNight.getText());
    }
}
