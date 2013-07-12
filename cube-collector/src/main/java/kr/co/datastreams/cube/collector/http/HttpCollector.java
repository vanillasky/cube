package kr.co.datastreams.cube.collector.http;

import kr.co.datastreams.cube.collector.*;
import kr.co.datastreams.cube.collector.event.LogEvent;
import kr.co.datastreams.cube.collector.listener.LogEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 9
 * Time: 오후 3:53
 * To change this template use File | Settings | File Templates.
 */
public abstract class HttpCollector implements Collector, LogEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(HttpCollector.class);

    protected List<LogEventListener> listeners = Collections.synchronizedList(new ArrayList<LogEventListener>());
    protected PageSupport paging;
    protected String url;
    protected HttpAgent agent;
    protected TrackQuery trackQuery = null;
    protected RequestMethod method;

    protected HttpCollector() {

    }

    public HttpCollector(String url, PageSupport paging, TrackQuery trackQuery, RequestMethod method) {
        this.paging = paging;
        this.url = url;
        this.agent = HttpAgent.create(url);
        this.trackQuery = trackQuery;
        this.method = method;
    }

    public HttpCollector(String url) {
        this(url, null, new TrackQuery(), RequestMethod.GET);
    }

    public HttpCollector(String url, TrackQuery trackQuery) {
        this(url, null, trackQuery, RequestMethod.GET);
    }


    @Override
    public void collect() {
        HttpAgentResponse response = request(buildParameters());
        handle(response.asString());
        if (paging != null) {
            while (paging.hasNext()) {
                paging = paging.next();
                response = request(buildParameters());
                handle(response.asString());
            }

            paging = paging.reset();
        }
    }


    protected HttpAgentResponse request(List<HttpParameter> parameters) {
        HttpAgentResponse response = null;
        if (method == RequestMethod.GET) {
            response = agent.get(buildURL(url, parameters.toArray(new HttpParameter[parameters.size()])));
        } else if (method == RequestMethod.POST) {
            response = agent.post(url, parameters.toArray(new HttpParameter[parameters.size()]));
        }

        return response;
    }


    protected String buildURL(String url, HttpParameter[] parameters) {
        if (parameters == null || parameters.length == 0) {
            return url;
        }
        return url + "?" + HttpParameter.encodeParameters(parameters);
    }

    @Override
    public void run() {
        collect();
    }

    @Override
    public void addTrackQuery(TrackQuery query) {
        trackQuery = trackQuery.add(query);
    }

    @Override
    public void registerListener(LogEventListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(LogEventListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    public void fireEvent(LogEvent event) {
        for (Iterator<LogEventListener> iter=listeners.iterator(); iter.hasNext();) {
            iter.next().onStatus(event);
        }
    }

    public void fireError(Exception ex) {
        for (Iterator<LogEventListener> iter=listeners.iterator(); iter.hasNext();) {
            iter.next().onError(ex);
        }
    }


    @Override
    abstract public void handle(Object source);

    abstract protected List<HttpParameter> buildParameters();

}
