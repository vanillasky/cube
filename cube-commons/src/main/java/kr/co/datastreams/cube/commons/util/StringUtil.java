package kr.co.datastreams.cube.commons.util;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 1
 * Time: 오후 4:44
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {

    public static String join(String[] values, String delimiter) {
        if (values == null)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < values.length; i++) {
            sb.append(values[i]).append(delimiter);
        }
        if (values.length > 0)
            return sb.substring(0, sb.lastIndexOf(delimiter));
        return sb.toString();
    }

    public static String nvl(String str) {
        return str == null ? "" : str;
    }



}
