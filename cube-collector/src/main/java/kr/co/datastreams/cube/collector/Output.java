package kr.co.datastreams.cube.collector;

import kr.co.datastreams.cube.collector.event.LogEvent;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 10
 * Time: 오후 5:25
 * To change this template use File | Settings | File Templates.
 */
public interface Output {
    public void out(LogEvent logEvent);
}
