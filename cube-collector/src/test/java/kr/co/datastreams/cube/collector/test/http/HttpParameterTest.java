package kr.co.datastreams.cube.collector.test.http;

import kr.co.datastreams.cube.collector.http.HttpParameter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 8
 * Time: 오전 10:31
 * To change this template use File | Settings | File Templates.
 */
public class HttpParameterTest {

    @Test
    public void testHttpParameter_equals() throws Exception {
        HttpParameter param = new HttpParameter("foo", "var");
        HttpParameter param2 = new HttpParameter("foo", "var");
        assertEquals(param, param2);
    }

    @Test
    public void testHttpParameter_encode() throws Exception {
        String expected = "foo=var&field_has_plus=%2B39";

        HttpParameter param = new HttpParameter("foo", "var");
        HttpParameter param2 = new HttpParameter("field_has_plus", "+39"); // + should be replace with %2B
        HttpParameter[] params = new HttpParameter[]{param, param2};
        String result = HttpParameter.encodeParameters(params);

        assertEquals(expected, result);
    }
}
