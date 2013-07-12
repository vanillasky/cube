package kr.co.datastreams.cube.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 10
 * Time: 오후 4:48
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");

    public static String formatDate(DateFormat readDateFormat, String strDate) {
        try {
            Date date = readDateFormat.parse(strDate);
            return DATE_FORMAT.format(date);
        } catch (ParseException e) {
            logger.warn("Cannot parse date {}, {} ", strDate, readDateFormat.toString());
            return strDate;
        }

    }

    public static Long asLong(DateFormat readDateFormat, String strDate) {
        try {
            Date date = readDateFormat.parse(strDate);
            return date.getTime();
        } catch (ParseException e) {
            logger.warn("Cannot parse date {}, {} ", strDate, readDateFormat.toString());
            return -1L;
        }
    }

    public static Date asDate(DateFormat readDateFormat, String strDate) {
        try {
            Date date = readDateFormat.parse(strDate);
            return date;
        } catch (ParseException e) {
            logger.warn("Cannot parse date {}, {} ", strDate, readDateFormat.toString());
            return new Date(System.currentTimeMillis());
        }
    }
}
