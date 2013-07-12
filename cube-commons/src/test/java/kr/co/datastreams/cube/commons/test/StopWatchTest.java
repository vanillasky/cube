package kr.co.datastreams.cube.commons.test;

import kr.co.datastreams.cube.commons.util.StopWatch;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오후 2:32
 * To change this template use File | Settings | File Templates.
 */
public class StopWatchTest {

    @Test
    public void testStopWatch() throws Exception {
        StopWatch watch = StopWatch.create();
        watch.start();
        Thread.sleep(1000);
        watch.end();

        assertEquals(String.valueOf("1.0"), String.valueOf(watch.elapcedInSecondes()));
    }
}
