package kr.co.datastreams.cube.collector.impl.naver;

import kr.co.datastreams.cube.collector.LogEventHandler;
import kr.co.datastreams.cube.collector.conf.Configuration;
import kr.co.datastreams.cube.collector.conf.ConfigurationFactory;
import kr.co.datastreams.cube.collector.event.ContentLog;
import kr.co.datastreams.cube.commons.util.DateUtil;
import kr.co.datastreams.cube.commons.util.JDOMUtil;
import org.jdom2.Document;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 10
 * Time: 오후 2:02
 * To change this template use File | Settings | File Templates.
 */
public class NaverNewsCollector extends NaverAPICollector {
    private static final Logger logger = LoggerFactory.getLogger(NaverAPICollector.class);
    private static final Configuration conf = ConfigurationFactory.getConfiguration("naver.news.conf");

    private Map<Long, String> duplicatedLogMap = new HashMap<Long, String>();

    public NaverNewsCollector(String[] keywords) {
        super(conf, keywords);
    }

    @Override
    public void handle(Object source) {
        if (source == null || !(source instanceof String)) {
            logger.warn("source object is {}", source == null ? "null" : "not String");
            return;
        }

        Document doc = null;
        try {
            doc = JDOMUtil.asDocument((String) source);
        } catch (IOException e) {
            logger.error("LogEvent process failed", e);
            fireError(e);
        }

        if (doc == null) return;

        Element root = doc.getRootElement();
        Element channel = root.getChild("channel");
        String track = channel.getChildText("title");
        String lastBuildDate = channel.getChildText("lastBuildDate");

        List<Element> items = channel.getChildren("item");
        for (Element item : items) {
            ContentLog event = new ContentLog();
            event.setSource(item.toString());
            event.setTitle(item.getChildText("title").replaceAll("\\n", " "));
            event.setLink(item.getChildText("originallink"));
            event.setCreatedAt(new Date(System.currentTimeMillis()));
            event.setPublishedAt(DateUtil.asDate(readDateFormat, item.getChildText("pubDate")));
            event.setTitle(item.getChildText("description"));
            event.setSource(track + "," + lastBuildDate);

            Long code = event.getTitle().hashCode() + DateUtil.asLong(readDateFormat, item.getChildText("pubDate"));
            if (duplicatedLogMap.containsKey(code)) {
                if (logger.isDebugEnabled()) logger.debug("Duplicated item, will be skipped:" + duplicatedLogMap.get(code));
            } else {
                duplicatedLogMap.put(code, event.getTitle());
                fireEvent(event);
            }
        }

        duplicatedLogMap.clear();
    }
}
