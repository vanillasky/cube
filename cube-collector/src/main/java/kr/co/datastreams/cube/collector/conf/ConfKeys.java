package kr.co.datastreams.cube.collector.conf;

/**
 *
 * User: shkim
 * Date: 13. 7. 5
 * Time: 오후 1:20
 *
 */
public interface ConfKeys {

    public static final String DATE_FORMAT = "date_format";
    public static final String DATE_FORMAT_LOCALE="date_format_locale";

    // for TimerTask
    public static final String TIME_UNIT = "time_unit";
    public static final String DELAY = "delay";
    public static final String INITIAL_DELAY = "initial_delay";

    // for openAPI base search
    public static final String SEARCH_API_URL = "search_api";
    public static final String PARAMETERS = "parameters";
    public static final String API_KEY = "api_key";
    public static final String TARGET = "target";
    public static final String ITEMS_PER_PAGE = "items_per_page";
    public static final String MAX_START_KEY = "max_start_key";
    public static final String SORT_OPTION = "sort_option";
    public static final String KEY_INCREMENT = "key_increment";

    // for Twitter
    public static final String OAUTH_CONSUMER_KEY = "OAuthConsumerKey";
    public static final String OAUTH_CONSUMER_SECRET = "OAuthConsumerSecret";
    public static final String OAUTH_ACCESS_TOKEN= "OAuthAccessToken";
    public static final String OAUTH_ACCESS_TOKEN_SECRET= "OAuthAccessTokenSecret";
    public static final String STREAM_API_URL = "stream_api_url";

}
