package kr.co.datastreams.cube.collector.test;

import kr.co.datastreams.cube.collector.Output;
import kr.co.datastreams.cube.collector.ScheduledCollectorService;
import kr.co.datastreams.cube.collector.event.LogEvent;
import kr.co.datastreams.cube.collector.http.HttpCollector;
import kr.co.datastreams.cube.collector.impl.naver.NaverNewsCollector;
import kr.co.datastreams.cube.collector.listener.LogEventListener;
import kr.co.datastreams.cube.collector.output.ConsoleOutput;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오후 1:02
 * To change this template use File | Settings | File Templates.
 */
public class NaverNewsCollectorTest implements LogEventListener {

    private Output output = new ConsoleOutput();
    ScheduledCollectorService service = null;

    public static void main(String[] args) {
        NaverNewsCollectorTest test = new NaverNewsCollectorTest();
        test.start();
    }

    private void start() {
        String[] keys = new String[]{"아시아나"};
        HttpCollector collector = new NaverNewsCollector(keys);
        collector.registerListener(this);

        service = new ScheduledCollectorService(collector);
        service.start();
    }

    @Test
    public void test() throws Exception {
        NaverNewsCollectorTest test = new NaverNewsCollectorTest();
        test.start();
        waitForStatus();
    }

    @Override
    public void onStatus(LogEvent event) {
        output.out(event);
        assertNotNull(event);
        notifyResponse();
    }

    private synchronized void notifyResponse() {
        this.notify();
        service.shutdown();

    }

    @Override
    public void onError(Exception e) {
        System.out.println(e);
    }



    private synchronized void waitForStatus() {
        try {
            this.wait(5000);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!! notified.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
