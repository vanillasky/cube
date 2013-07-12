package kr.co.datastreams.cube.collector.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 8
 * Time: 오전 9:47
 * To change this template use File | Settings | File Templates.
 */
public class HttpParameter implements Comparable, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(HttpParameter.class);
    private static final long serialVersionUID = 1651846548466533234L;
    private final String name;
    private final String value;

    public HttpParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public HttpParameter(String name, int value) {
        this(name, String.valueOf(value));
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(Object o) {
        HttpParameter other = (HttpParameter)o;
        int result = name.compareTo(other.name);
        if (result == 0) {
            result = value.compareTo(other.value);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof HttpParameter)) {
            return false;
        }

        HttpParameter other = (HttpParameter)o;
        if (!name.equals(other.name)) {
            return false;
        }

        if (value != null ? !value.equals(other.value) : other.value != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public static String encodeParameters(HttpParameter[] params) {
        if (params == null) {
            return "";
        }

        StringBuilder buf = new StringBuilder();
        for (int i=0; i < params.length; i++) {
            buf.append(encode(params[i].name)).append("=").append(encode(params[i].value)).append("&");
        }

        if (buf.length() > 1) {
            return buf.substring(0, buf.length()-1);
        }

        return buf.toString();

    }

    public static String encode(String str) {
        String encoded = null;
        try {
            encoded = URLEncoder.encode(str, HttpAgent.ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.warn("UnSupportedEncoding {}", e, HttpAgent.ENCODING);
        }
        assert encoded != null;
        StringBuilder buf = new StringBuilder(encoded.length());
        char current;
        for (int i=0; i  < encoded.length(); i++) {
            current = encoded.charAt(i);
            if (current == '*') {
                buf.append("%2A");
            } else if (current == '+') {
                buf.append("%20");

            } else if (current == '%' && (i+1) < encoded.length() && encoded.charAt(i+1) == '7' && encoded.charAt(i+2) == 'E') {
                buf.append("~");
                i += 2;
            } else {
                buf.append(current);
            }

        }
        return buf.toString();
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("HttpParameter { ").append("name:").append(name).append(",")
           .append("value:").append(value).append("}");
        return buf.toString();
    }

}
