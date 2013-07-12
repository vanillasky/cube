package kr.co.datastreams.cube.collector;

import kr.co.datastreams.cube.collector.event.LogEvent;
import kr.co.datastreams.cube.collector.listener.LogEventListener;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 10
 * Time: 오후 2:58
 * To change this template use File | Settings | File Templates.
 */
public interface LogEventHandler {
    public void handle(Object source);
    void registerListener(LogEventListener listener);
    void removeListener(LogEventListener listener);
}
