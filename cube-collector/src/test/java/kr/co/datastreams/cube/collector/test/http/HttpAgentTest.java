package kr.co.datastreams.cube.collector.test.http;

import kr.co.datastreams.cube.collector.CollectorException;
import kr.co.datastreams.cube.collector.http.HttpAgent;
import kr.co.datastreams.cube.collector.http.HttpAgentResponse;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 8
 * Time: 오전 10:42
 * To change this template use File | Settings | File Templates.
 */
public class HttpAgentTest {


    @Test(expected= CollectorException.class)
    public void testHttpGet_404NotFound() throws  Exception {
        HttpAgent client = HttpAgent.create("http://www.woodenplanet.net/indexx.jsp");
        client.get();
    }


    @Test
    public void testHttpGet_200OK() throws  Exception {
        HttpAgent client = HttpAgent.create("http://www.woodenplanet.net/index.jsp");
        HttpAgentResponse response = client.get();
        assertEquals(200, response.getStatusCode());
    }

    //TODO: Test with parameters
    @Test
    public void testHttpPost() throws Exception {
        HttpAgent client = HttpAgent.create("http://www.woodenplanet.net/index.jsp");
        HttpAgentResponse response = client.post();
//        System.out.println(response.asString());
        assertEquals(200, response.getStatusCode());
    }


}
