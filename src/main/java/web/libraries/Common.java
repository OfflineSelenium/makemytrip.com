package web.libraries;

import org.apache.xmlbeans.impl.store.Locale;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;



public class Common {

    /**
     * Sleep for certain milliseconds
     */
    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (final InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Copy a file from point A to point B
     */
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists())
            destFile.createNewFile();

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null)
                source.close();
            if (destination != null)
                destination.close();
        }
    }

    /**
     * Convert a Color variable as RGB format to Hex format
     */
    public static String convertToHex(String color) {
        String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");

        int hexValue1 = Integer.parseInt(hexValue[0]);
        hexValue[1] = hexValue[1].trim();
        int hexValue2 = Integer.parseInt(hexValue[1]);
        hexValue[2] = hexValue[2].trim();
        int hexValue3 = Integer.parseInt(hexValue[2]);

        return String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
    }

    public static int[] splitDate(String date) {
        String[] daymonthyear = date.split("/");
        int day = Integer.parseInt(daymonthyear[1]);
        int month = Integer.parseInt(daymonthyear[0]);
        int year = Integer.parseInt(daymonthyear[2]);
        int[] DMY = new int[3];
        DMY[1] = day;
        DMY[0] = month;
        DMY[2] = year;
        return DMY;
    }

    public static Date string2Date(String stringDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date_Date = null;
        {
            try {
                date_Date = sdf.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date_Date;
    }

    public static String verifyCheckInDate(String checkInDate) throws ParseException {
        Date todayDate;
        int date;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        todayDate = sdf.parse(sdf.format(new Date()));
        if (todayDate.compareTo(string2Date(checkInDate)) >= 0) {
            System.out.println("------------------------------------------------");
            System.out.println("CHECKIN DATE MUST BE EQUAL OR GREATER THAN TODAY");
            System.out.println("------------------------------------------------");
            return checkInDate = sdf.format(todayDate);
            //date = Common.splitDate(checkInDate)[1];
            //return date;
        }
        //return Common.splitDate(checkInDate)[1];
        return checkInDate;
    }

    public static boolean verifyCheckOutDate(String checkInDate,String checkOutDate) throws ParseException {
        System.out.println("-----------------verifyCheckOutDate");
        if (string2Date(checkOutDate).compareTo(string2Date(checkInDate)) < 0) {
            System.out.println("------------------------------------------------");
            System.out.println("CHECKOUT DATE MUST BE GREATER THAN CHECKIN DATE");
            System.out.println("------------------------------------------------");
            return false;
        }
        else
            return true;
    }

    public static int convertMonthString2Int(String monthStr) throws ParseException {
        Date date = new SimpleDateFormat("MMM").parse(monthStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }
}
