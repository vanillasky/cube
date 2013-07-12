package kr.co.datastreams.cube.collector;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 11
 * Time: 오전 8:23
 * To change this template use File | Settings | File Templates.
 */
public interface ScheduledCollector extends Collector {
    public Schedule getSchedule();
}
