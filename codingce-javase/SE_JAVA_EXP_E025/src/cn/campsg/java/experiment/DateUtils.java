package cn.campsg.java.experiment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zhao Jiaxin
 */
public final class DateUtils {

    public static final int STANDARD_RET_DATE = 15;

    public static String getReturnDate(int divDate) {
        if (divDate > STANDARD_RET_DATE)
            return null;
        else {
            Date date = new Date();
            return new SimpleDateFormat("yyyy-MM-dd").format(date.getTime() + divDate * 24 * 60 * 60 * 1000);
        }
    }

    private DateUtils() {
        super();
    }

    public static String getReturnDate() {
        return getReturnDate(STANDARD_RET_DATE);
    }
}