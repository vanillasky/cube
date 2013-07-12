package kr.co.datastreams.cube.collector;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 4
 * Time: 오후 4:49
 *
 */
public class BasePaging implements PageSupport, Serializable {

    private static final int DEFAULT_DISPLAY = 100;
    private static final int DEFAULT_MAX_START_KEY = 1000;

    private final int maxStartId;
    private final int itemsPerPage;
    private final int increment;
    private final int currentId;

    public BasePaging() {
        this(DEFAULT_DISPLAY, DEFAULT_MAX_START_KEY, DEFAULT_DISPLAY);
    }

    public BasePaging(int itemsPerPage, int maxStartKey, int increment) {
        this.maxStartId = maxStartKey;
        this.itemsPerPage = itemsPerPage;
        this.increment = increment;
        this.currentId = 1;
    }

    private BasePaging(int itemsPerPage, int maxStartKey, int increment, int startKey) {
        this.maxStartId = maxStartKey;
        this.itemsPerPage = itemsPerPage;
        this.increment = increment;
        this.currentId = startKey;
    }

    @Override
    public int maxStartId() {
        return maxStartId;
    }

    @Override
    public int itemsPerPage() {
        return itemsPerPage;
    }


    @Override
    public boolean hasNext() {
        return currentId < maxStartId ? true : false;
    }

    @Override
    public PageSupport reset() {
        return new BasePaging(itemsPerPage, maxStartId, increment, 1);
    }

    @Override
    public int startId() {
        return currentId;
    }

    @Override
    public PageSupport next() {
        if (!hasNext()) {
            throw new IllegalStateException("max key excess - max:" + maxStartId + ", requested:" + (currentId + increment) );
        }

        int nextStartKey = currentId + increment;
        if (nextStartKey > maxStartId && currentId < maxStartId) {
            nextStartKey = maxStartId;
        }

        return new BasePaging(itemsPerPage, maxStartId, increment, nextStartKey);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{BasePaging: maxStartId=").append(maxStartId())
          .append(", currentId= ").append(startId()).append("}");

        return sb.toString();
    }
}
