package kr.co.datastreams.cube.collector.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * User: shkim
 * Date: 13. 7. 3
 * Time: 오전 8:24
 *
 * Configuration 객체를 만들어 반환한다.
 * conf/logcube.properties 파일을 읽어 사용할 프로퍼티의 종류를 파악하여(DB 또는 FILE)
 * FILE인 경우 FileConfiguration을, DB이면 DBConfiguration 객체를 반환한다.
 *
 *
 */
public class ConfigurationFactory {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationFactory.class);
    private static final String LOG_CONFIGURATION_FIFLE = "/conf/logcube.properties";
    private static final String LOG_CONFIGURATION = "logcube.configuration";

    //private static final ConfigurationFactory INSTANCE = new ConfigurationFactory();
    private static final Map<String, Configuration> PROPS_CACHE = new HashMap<String, Configuration>();
    private static final Configuration sysConf = ClassPathConfiguration.newInstance(LOG_CONFIGURATION_FIFLE);

    private ConfigurationFactory() {

    }

    public static Configuration getConfiguration() {
        return sysConf;
    }

    public static Configuration getConfiguration(String key) {
        return resovleConfiguration(key);
    }

    private static Configuration resovleConfiguration(String key) {
        String confSource = sysConf.get(LOG_CONFIGURATION);

        if (confSource.equalsIgnoreCase("FILE")) {
            return FileConfiguration.newInstance(key);
        } else {
            //TODO DBConfiguration 구현 후 적용
            return DBConfiguration.newInstance(key);
        }
    }

}
