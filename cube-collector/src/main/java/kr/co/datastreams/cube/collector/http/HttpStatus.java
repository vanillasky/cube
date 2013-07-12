package kr.co.datastreams.cube.collector.http;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오전 9:27
 *
 */
public class HttpStatus {
    public static final Map<Integer, String> messages = new HashMap<Integer, String>();

    private HttpStatus() {

    }

    static {
        messages.put(HttpStatusCode.BAD_REQUEST, "The request cannot be fulfilled due to bad syntax.");
        messages.put(HttpStatusCode.FORBIDDEN, "The request was a valid request, but the server is refusing to respond to it.");
        messages.put(HttpStatusCode.NOT_ACCEPTABLE, "The requested resource is only capable of generating content not acceptable according to the Accept headers sent in the request.");
        messages.put(HttpStatusCode.NOT_FOUND, "The requested resource could not be found but may be available again in the future.");
        messages.put(HttpStatusCode.UNAUTHORIZED, "Authentication is required and has failed or has not yet been provided.");
    }

    public static String getMessage(int code) {
        return messages.get(code) == null ? "Message not found for code " + code : messages.get(code);
    }

}
