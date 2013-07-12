package kr.co.datastreams.cube.collector.output;

import kr.co.datastreams.cube.collector.Output;
import kr.co.datastreams.cube.collector.event.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 10
 * Time: 오후 5:26
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleOutput implements Output {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleOutput.class);

    @Override
    public void out(LogEvent logEvent) {
        logger.debug("DDDDDDDDDDD");
        System.out.println("LOg EVENT:" + logEvent);
    }
}
