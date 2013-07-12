package kr.co.datastreams.cube.collector;


import kr.co.datastreams.cube.collector.http.HttpStatus;
import kr.co.datastreams.cube.collector.http.HttpStatusCode;

/**
 *
 * User: shkim
 * Date: 13. 7. 8
 * Time: 오후 12:44
 *
 * collector 모듈에서 발생하는 런타임 에러
 */
public class CollectorException extends RuntimeException implements HttpStatusCode {

    private static final long serialVersionUID = -3211917813312758292L;
    private int statusCode;
    private String message;

    public CollectorException(String message, int statusCode) {
        super(statusCode + " - " + message);
        this.statusCode = statusCode;
        this.message = message;
    }

    public CollectorException(String message) {
        super();
        this.message = message;
    }

    public CollectorException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        StringBuilder buf = new StringBuilder();
        if (message != null) {
            buf.append("message - ").append(message);
        }
        else {
            buf.append(super.getMessage());
        }

        if (statusCode > 0) {
            return getCause(statusCode) + "\n" + buf.toString();
        } else {
            return buf.toString();
        }

    }

    private static String getCause(int statusCode) {
        return statusCode + ":" + HttpStatus.getMessage(statusCode);
    }

}
