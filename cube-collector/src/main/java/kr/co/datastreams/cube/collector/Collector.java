package kr.co.datastreams.cube.collector;

import kr.co.datastreams.cube.collector.listener.LogEventListener;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오후 3:54
 * To change this template use File | Settings | File Templates.
 */
public interface Collector extends Runnable {
    public void collect();
    public void addTrackQuery(TrackQuery query);
    public void registerListener(LogEventListener listener);
    public void removeListener(LogEventListener listener);
}
