package kr.co.datastreams.cube.collector;


import kr.co.datastreams.cube.commons.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오전 10:48
 *
 * Immutable
 */

public class TrackQuery implements Serializable {

    private static final long serialVersionUID = 33479559961261953L;
    private static Logger logger = LoggerFactory.getLogger(TrackQuery.class);
//    private static final ListM EMPTY = new TrackQuery(new String[]{});

    private final List<String> keywords;

    public TrackQuery() {
        this.keywords = Collections.EMPTY_LIST;
    }

    public TrackQuery(String... keywords) {
        if (keywords == null) {
            this.keywords = Collections.EMPTY_LIST;
        } else {
            this.keywords = Arrays.asList(keywords);
        }
    }

    public String[] getKeywords() {
        return keywords.toArray(new String[keywords.size()]);
    }

    public boolean isEmpty() {
        return keywords.size() == 0 ? true : false;
    }

    @Override
    public int hashCode() {
        return keywords != null ? keywords.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackQuery other = (TrackQuery)o;
        return keywords.equals(other.keywords);
    }

    public TrackQuery add(TrackQuery query) {
        String[] addedKeywords = query.getKeywords();
        List<String> keywordList = new ArrayList<String>(this.keywords.size() + addedKeywords.length);
        copyKeywordsTo(keywordList, getKeywords());

        for (String each : addedKeywords) {
            if (!keywordList.contains(each)) {
                keywordList.add(each);
            }
        }
        return new TrackQuery(keywordList.toArray(new String[keywordList.size()]));
    }

    private void copyKeywordsTo(List<String> keywordList, String[] src) {
        Collections.addAll(keywordList, src);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("TrackQuery {")
           .append("keywords: [")
           .append(StringUtil.join(getKeywords(), ", "))
           .append("]}");
        return buf.toString();
    }

    public TrackQuery addKeywords(String[] addedKeywords) {
        return add(new TrackQuery(addedKeywords));
    }
}
