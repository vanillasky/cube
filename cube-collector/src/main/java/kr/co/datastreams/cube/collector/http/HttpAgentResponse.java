package kr.co.datastreams.cube.collector.http;

import kr.co.datastreams.cube.collector.CollectorException;
import org.apache.http.HttpRequest;
import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 8
 * Time: 오전 11:21
 * To change this template use File | Settings | File Templates.
 */
public class HttpAgentResponse {
    private static final Logger logger = LoggerFactory.getLogger(HttpAgentResponse.class);

    private String responseAsString;
    private int statusCode;
    private HttpRequest request;
    private InputStream stream;
    private boolean streamConsumed;
    private JsonNode json;


    public HttpAgentResponse(InputStream stream, HttpRequest request, int statusCode) {
        this.stream = stream;
        this.request =request;
        this.statusCode = statusCode;
    }

    public InputStream asStream() {
        if (streamConsumed) {
            throw new IllegalStateException("Stream has already been consumed.");
        }
        return stream;
    }

    public void disconnect() {
        try {
            stream.close();
        } catch (IOException e) {
            logger.error("An Exception occurred while closing InputStream", e);
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String asString() {
        if (responseAsString != null) {
            return responseAsString;
        }

        BufferedReader reader= null;
        InputStream is = asStream();
        if (stream == null) {
            return null;
        }

        try {
            reader = new BufferedReader(new InputStreamReader(is, HttpAgent.ENCODING));
            StringBuilder buf = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line).append("\n");
            }

            responseAsString = buf.length() > 1 ? buf.substring(0, buf.length()-1) : buf.toString();
            streamConsumed = true;
        } catch (IOException e) {
            throw new CollectorException(e.getMessage(), e);
        } finally {
            if (is != null) {
                try { is.close(); } catch (IOException e) {logger.warn(e.getMessage(), e);}
            }
            if (reader != null) {
                try { reader.close(); } catch (IOException e) {logger.warn(e.getMessage(), e);}
            }
        }

        return responseAsString;
    }


}
