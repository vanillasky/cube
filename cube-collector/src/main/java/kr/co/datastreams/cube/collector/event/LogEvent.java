package kr.co.datastreams.cube.collector.event;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오전 9:47
 * To change this template use File | Settings | File Templates.
 */
public interface LogEvent {
    Date getCreatedAt();
    long getId();
    String getText();
    String getSource();
    String getLogLevel();

}
