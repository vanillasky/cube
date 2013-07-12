package kr.co.datastreams.cube.collector.impl.twitter;

import kr.co.datastreams.cube.collector.TrackQuery;
import kr.co.datastreams.cube.collector.conf.ConfKeys;
import kr.co.datastreams.cube.collector.conf.Configuration;
import kr.co.datastreams.cube.collector.event.ContentLog;
import kr.co.datastreams.cube.collector.event.ContentLogEvent;
import kr.co.datastreams.cube.collector.http.HttpAgentResponse;
import kr.co.datastreams.cube.collector.http.HttpCollector;
import kr.co.datastreams.cube.collector.http.HttpParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 11
 * Time: 오전 8:22
 * To change this template use File | Settings | File Templates.
 */
public class TwitterStreamCollector extends BaseTwitterCollector {

    private static final Logger logger = LoggerFactory.getLogger(TwitterStreamCollector.class);

    private TwitterStream twitterStream;

    public TwitterStreamCollector(Configuration conf) {
        this(conf, new TrackQuery());
    }

    public TwitterStreamCollector(Configuration conf, String... keywords) {
        this(conf, new TrackQuery(keywords));
    }

    public TwitterStreamCollector(Configuration conf, TrackQuery trackQuery) {
        super(conf, trackQuery);
    }


    @Override
    protected List<HttpParameter> buildParameters() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void collect() {
        ConfigurationBuilder tconf = configure();
        twitterStream = new TwitterStreamFactory(tconf.build()).getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                handle(status);
            }
            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int i) {
                fireError(new IllegalStateException("Track Limitation noticed:" + i));
            }

            @Override
            public void onScrubGeo(long l, long l2) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onException(Exception e) {
                fireError(e);
            }
        };

        twitterStream.addListener(listener);
        twitterStream.filter(new FilterQuery().track(trackQuery.getKeywords()));
    }

    @Override
    public void shutdown() {
        twitterStream.shutdown();
    }


}
