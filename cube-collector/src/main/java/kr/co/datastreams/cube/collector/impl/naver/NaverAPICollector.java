package kr.co.datastreams.cube.collector.impl.naver;

import kr.co.datastreams.cube.collector.BasePaging;
import kr.co.datastreams.cube.collector.Schedule;
import kr.co.datastreams.cube.collector.ScheduledCollector;
import kr.co.datastreams.cube.collector.TrackQuery;
import kr.co.datastreams.cube.collector.conf.ConfKeys;
import kr.co.datastreams.cube.collector.conf.Configuration;
import kr.co.datastreams.cube.collector.event.ContentLog;
import kr.co.datastreams.cube.collector.http.HttpAgent;
import kr.co.datastreams.cube.collector.http.HttpCollector;
import kr.co.datastreams.cube.collector.http.HttpParameter;
import kr.co.datastreams.cube.collector.http.RequestMethod;
import kr.co.datastreams.cube.commons.util.DateUtil;
import kr.co.datastreams.cube.commons.util.JDOMUtil;
import kr.co.datastreams.cube.commons.util.StringUtil;
import org.jdom2.Document;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오후 12:50
 * To change this template use File | Settings | File Templates.
 */
public abstract class NaverAPICollector extends HttpCollector implements ConfKeys, ScheduledCollector {

    protected final DateFormat readDateFormat;
    protected Configuration conf;
    protected Schedule schedule;

    public NaverAPICollector(Configuration conf) {
        this(conf, new TrackQuery());
    }

    public NaverAPICollector(Configuration conf, String... kewords) {
        this(conf, new TrackQuery(kewords));
    }

    public NaverAPICollector(Configuration conf, TrackQuery query) {
        paging = new BasePaging(conf.getInt(ITEMS_PER_PAGE), conf.getInt(MAX_START_KEY), conf.getInt(KEY_INCREMENT));
        url = conf.get(SEARCH_API_URL);
        agent = HttpAgent.create(url);
        trackQuery = query;
        method = RequestMethod.GET;
        schedule = new Schedule(conf.getInt(INITIAL_DELAY), conf.getInt(DELAY), conf.get(TIME_UNIT));
        this.conf = conf;
        readDateFormat = new SimpleDateFormat(conf.get(DATE_FORMAT), Locale.ENGLISH);
    }


    @Override
    public Schedule getSchedule() {
        return schedule;
    }


    @Override
    public List<HttpParameter> buildParameters() {
        List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(new HttpParameter("key", conf.get(API_KEY)));
        parameters.add(new HttpParameter("target", conf.get(TARGET)));
        parameters.add(new HttpParameter("sort", conf.get(SORT_OPTION)));
        parameters.add(new HttpParameter("display", conf.get(ITEMS_PER_PAGE)));

        if (paging != null) {
            parameters.add(new HttpParameter("start", paging.startId()));
        }

        if (!trackQuery.isEmpty()) {
            parameters.add(new HttpParameter("query", StringUtil.join(trackQuery.getKeywords(), ",")));
        }

        return parameters;

    }

}
