package kr.co.datastreams.cube.collector.http;


import kr.co.datastreams.cube.collector.CollectorException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.*;


/**
 *
 * User: shkim
 * Date: 13. 7. 8
 * Time: 오전 10:43
 *
 * Http request를 처리한다.
 * GET, POST 메소드를 이용하여 URL을 전송하고, 결과를 받아서 HttpAgentResponse에 담아서 반환한다.
 *
 * Code Snippet:
 *
 * HttpAgent client = HttpAgent.create("http://www.woodenplanet.net/index.jsp");
 * client.addParameter(new HttpParameter("foo", "var"));
 * HttpAgentResponse response = client.post();
 * System.out.println(response.asString());
 *
 */
public class HttpAgent implements HttpStatusCode {

    private static final Logger logger = LoggerFactory.getLogger(HttpAgent.class);
    private static final Map<String, HttpAgent> cache = new HashMap<String, HttpAgent>(); // cache for each url
    public static final String ENCODING = "UTF-8";

    private String url;
    private List<HttpParameter> parameters = Collections.synchronizedList(new ArrayList<HttpParameter>());

    private HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
        @Override
        public boolean retryRequest(IOException ex, int executionCount, HttpContext context) {
            if (executionCount >=5) return false;
            if (ex instanceof InterruptedIOException) return false;
            if (ex instanceof UnknownHostException) return false;
            if (ex instanceof ConnectException) return false;
            if (ex instanceof SSLException) return false;
            HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                // Retry if the request is considered idempotent
                return true;
            }

            return false;
        }
    };

    private HttpAgent(String url) {
        this.url = url;
    }

    public static HttpAgent create(String url) {
        HttpAgent agent = cache.get(url);
        if (agent == null) {
            agent = new HttpAgent(url);
            cache.put(url, agent);
        }
        return agent;
    }

    public void addParameter(HttpParameter param) {
        parameters.add(param);
    }

    /**
     * HTTP GET 메소드를 이용하여 Request를 전송하고 데이터를 받아서 반환한다.
     * @return HttpAgentResponse
     */
    public HttpAgentResponse get() {
        String requestUrl= url + "?" + HttpParameter.encodeParameters(parameters.toArray(new HttpParameter[parameters.size()]));
        return get(requestUrl);
    }


    /**
     * HTTP GET 메소드를 이용하여 Request를 전송하고 데이터를 받아서 반환한다.
     * @param url -  the url for http get request
     * @return HttpAgentResponse
     */
    public HttpAgentResponse get(String url) {
        HttpGet request = new HttpGet();
        request.setURI(URI.create(url));
        HttpClient client = prepareClient(url);

        if (logger.isDebugEnabled()) {
            logger.debug("Thread-[{}] Request:{}", Thread.currentThread().getId(), request);
        }


        try {
            return handleResult(client.execute(request), request);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new CollectorException(e.getMessage(), e);
        }
    }

    /**
     * HTTP POST 메소드를 이용하여 Request를 전송하고 데이터를 받아서 반환한다.
     * @return HttpAgentResponse
     */
    public HttpAgentResponse post() {
        return post(this.url, this.parameters.toArray(new HttpParameter[this.parameters.size()]));
    }

    /**
     * HTTP POST 메소드를 이용하여 Request를 전송하고 데이터를 받아서 반환한다.
     * @param url - request url
     * @param params - name/value pair parameters
     * @return HttpAgentResponse - the result of http request
     */
    public HttpAgentResponse post(String url, HttpParameter[] params) {
        HttpPost request = new HttpPost(url);
        HttpClient client = prepareClient(url);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        for (HttpParameter param : parameters) {
            nameValuePairs.add(new BasicNameValuePair(param.getName(), param.getValue()));
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Thread-[{}] Request:{}", Thread.currentThread().getId(), request);
        }

        try {
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            return handleResult(client.execute(request), request);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            throw new CollectorException(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new CollectorException(e.getMessage(), e);
        }
    }


    private HttpAgentResponse handleResult(HttpResponse response, HttpRequest request) {
        if (response.getStatusLine().getStatusCode() > BAD_REQUEST) {
            logger.error("Http Error: {} {}", response.getStatusLine().getStatusCode() + " - " + response.getStatusLine().getReasonPhrase(), request);
            throw new CollectorException(response.getStatusLine().getReasonPhrase(), response.getStatusLine().getStatusCode());
        }

        try {
            return new HttpAgentResponse(response.getEntity().getContent(), request, response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new CollectorException(e.getMessage(), e);
        }
    }

    private HttpClient prepareClient(String url) {
        HttpClient client = new DefaultHttpClient();
        ((DefaultHttpClient)client).setHttpRequestRetryHandler(retryHandler);
        return client;
    }
}


