package kr.co.datastreams.cube.collector.impl.twitter;

import kr.co.datastreams.cube.collector.TrackQuery;
import kr.co.datastreams.cube.collector.conf.ConfKeys;
import kr.co.datastreams.cube.collector.conf.Configuration;
import kr.co.datastreams.cube.collector.event.BaseUser;
import kr.co.datastreams.cube.collector.event.ContentLog;
import kr.co.datastreams.cube.collector.event.LogEvent;
import kr.co.datastreams.cube.collector.http.HttpCollector;
import kr.co.datastreams.cube.collector.http.HttpParameter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 11
 * Time: 오전 10:25
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseTwitterCollector extends HttpCollector implements ConfKeys {
    private static final Logger logger = LoggerFactory.getLogger(BaseTwitterCollector.class);

    protected final Configuration conf;
    protected final TrackQuery trackQuery;

    public BaseTwitterCollector(Configuration conf) {
        this(conf, new TrackQuery());
    }

    public BaseTwitterCollector(Configuration conf, String... keywords) {
        this(conf, new TrackQuery(keywords));
    }

    public BaseTwitterCollector(Configuration conf, TrackQuery trackQuery) {
        this.conf = conf;
        this.trackQuery = trackQuery;
    }

    @Override
    public void handle(Object source) {
        Status status = (Status)source;
        ContentLog event = new ContentLog();
        event.setId(status.getId());
        event.setCreatedAt(new Date(System.currentTimeMillis()));
        event.setPublishedAt(status.getCreatedAt());
        event.setText(status.getText());

        BaseUser user = new BaseUser();
        user.setName(status.getUser().getName());
        user.setLocation(status.getUser().getLocation());
        user.setLang(status.getUser().getLang());
        event.setLink(status.getUser().getURL());

        fireEvent(event);
    }


    @Override
    protected List<HttpParameter> buildParameters() {
        return null;
    }

    @Override
    abstract public void collect();

    abstract public void shutdown();

    protected ConfigurationBuilder configure() {
        ConfigurationBuilder tconf = new ConfigurationBuilder();
        tconf.setDebugEnabled(false);
        tconf.setOAuthConsumerKey(conf.get(OAUTH_CONSUMER_KEY));
        tconf.setOAuthConsumerSecret(conf.get(OAUTH_CONSUMER_SECRET));
        tconf.setOAuthAccessToken(conf.get(OAUTH_ACCESS_TOKEN));
        tconf.setOAuthAccessTokenSecret(conf.get(OAUTH_ACCESS_TOKEN_SECRET));
        tconf.setJSONStoreEnabled(true);

        return tconf;
    }
}
