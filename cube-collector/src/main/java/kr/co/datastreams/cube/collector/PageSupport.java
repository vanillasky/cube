package kr.co.datastreams.cube.collector;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 1
 * Time: 오후 2:46
 * To change this template use File | Settings | File Templates.
 */
public interface PageSupport {
    public int itemsPerPage();
    public PageSupport next();
    public boolean hasNext();
    public PageSupport reset();
    public int startId();
    public int maxStartId();
}
