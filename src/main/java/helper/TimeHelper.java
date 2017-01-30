package helper;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Time helper
 *
 * @author Vladimir Shkerin
 * @since 30.01.2017
 */
@SuppressWarnings("UnusedDeclaration")
public class TimeHelper {

    public static long getTimeInMs() {
        Date date = new Date();
        return date.getTime();
    }

    public static int getPOXIS() {
        Date date = new Date();
        int millisInSecond = 1000;
        return (int) (date.getTime() / millisInSecond);
    }

    public static String getUserDateFull(Locale locale) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
        return dateFormat.format(date);
    }

}
