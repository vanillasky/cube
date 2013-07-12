package kr.co.datastreams.cube.collector.listener;

import kr.co.datastreams.cube.collector.event.LogEvent;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오전 9:51
 * To change this template use File | Settings | File Templates.
 */
public interface LogEventListener {
    public void onStatus(LogEvent event);
    public void onError(Exception e);
}
