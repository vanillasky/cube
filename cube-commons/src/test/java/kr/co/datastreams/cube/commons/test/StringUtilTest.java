package kr.co.datastreams.cube.commons.test;

import kr.co.datastreams.cube.commons.util.StringUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 10
 * Time: 오후 5:44
 * To change this template use File | Settings | File Templates.
 */
public class StringUtilTest {

    @Test
    public void testJoin() throws Exception {
        String[] arr = {"1", "2", "3"};
        String expected = "1,2,3";
        String result = StringUtil.join(arr, ",");
        assertEquals(expected, result);
    }

    @Test
    public void testJoin_with_null() throws Exception {
        String[] arr = null;
        String result = StringUtil.join(arr, ",");
        assertNull(result);
    }

    @Test
    public void testJoin_with_single_value() throws Exception {
        String[] arr = {"1"};
        String result = StringUtil.join(arr, ",");
        assertEquals("1", result);
    }
}
