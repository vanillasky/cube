package kr.co.datastreams.cube.collector;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 10
 * Time: 오후 2:30
 * To change this template use File | Settings | File Templates.
 */
public class Schedule {

    public final int initialDelay;
    public final int delay;
    public final String timeUnit;

    public Schedule(int initialDelay, int delay, String timeUnit) {
        this.initialDelay = initialDelay;
        this.delay = delay;
        this.timeUnit = timeUnit;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("Schedule {")
           .append("initialDelay: ").append(initialDelay).append(", ")
           .append("delay: ").append(delay).append(", ")
           .append("timeUnit: ").append(timeUnit).append("}");

        return buf.toString();
    }
}

