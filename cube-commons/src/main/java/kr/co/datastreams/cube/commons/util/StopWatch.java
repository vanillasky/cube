package kr.co.datastreams.cube.commons.util;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오후 2:13
 * To change this template use File | Settings | File Templates.
 */
public class StopWatch {

    private long start;
    private long end;


    private StopWatch() {
        this.start = 0L;
        this.end = 0L;
    }

    public static StopWatch create() {
        return new StopWatch();
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public void end() {
        end = System.currentTimeMillis();
    }

    public long getStartTime() {
        return start;
    }

    public long getEndTime() {
        return end;
    }

    public long elapsed() {
        return end - start;
    }

    public double elapcedInSecondes() {
        return (double)elapsed() / 1000D;
    }

}
