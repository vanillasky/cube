package kr.co.datastreams.cube.collector.test;

import kr.co.datastreams.cube.collector.LogEventHandler;
import kr.co.datastreams.cube.collector.Output;
import kr.co.datastreams.cube.collector.ScheduledCollectorService;
import kr.co.datastreams.cube.collector.conf.Configuration;
import kr.co.datastreams.cube.collector.conf.ConfigurationFactory;
import kr.co.datastreams.cube.collector.event.LogEvent;
import kr.co.datastreams.cube.collector.http.HttpCollector;
import kr.co.datastreams.cube.collector.impl.naver.NaverNewsCollector;
import kr.co.datastreams.cube.collector.impl.twitter.TwitterStreamCollector;
import kr.co.datastreams.cube.collector.listener.LogEventListener;
import kr.co.datastreams.cube.collector.output.ConsoleOutput;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 11
 * Time: 오전 8:54
 * To change this template use File | Settings | File Templates.
 */
public class TwitterStreamCollectorTest implements LogEventListener {

    private Output output = new ConsoleOutput();
    private void start() {
        Configuration conf = ConfigurationFactory.getConfiguration("user1.twitter.conf");
        String[] keys = new String[]{"movie"};
        HttpCollector collector = new TwitterStreamCollector(conf, keys);
        collector.registerListener(this);
        collector.collect();
    }

    @Test
    public void test() throws Exception {
        TwitterStreamCollectorTest test = new TwitterStreamCollectorTest();
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
