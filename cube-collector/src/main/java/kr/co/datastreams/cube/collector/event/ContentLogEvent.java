package kr.co.datastreams.cube.collector.event;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오전 9:52
 * To change this template use File | Settings | File Templates.
 */
public interface ContentLogEvent extends LogEvent {
    public User getAuthor();
    public String getTitle();
    public String getLink();
    public Integer getReplyCount();
    public Integer getQuoteCount();
    public Date getPublishedAt();

}
