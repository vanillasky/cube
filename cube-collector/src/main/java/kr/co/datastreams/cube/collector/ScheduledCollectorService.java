package kr.co.datastreams.cube.collector;

import kr.co.datastreams.cube.collector.conf.ConfKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 10
 * Time: 오전 11:26
 * To change this template use File | Settings | File Templates.
 */
public class ScheduledCollectorService implements ConfKeys {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledCollectorService.class);


    private final ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
    private final ScheduledCollector task;


    public ScheduledCollectorService(Collector task) {
        if (!(task instanceof Runnable)) {
            throw new CollectorException("Not Runnable object");
        }

        if (!(task instanceof ScheduledCollector)) {
            throw new CollectorException("Schedulable should be implemented");
        }

        this.task = (ScheduledCollector)task;

    }

    public void start() {
        Schedule schedule = this.task.getSchedule();
        if (logger.isInfoEnabled()) {
            logger.info("Scheduled collector service starts, task: {}, initial delay: {}, delay: {}. ",
                   task.getClass().getSimpleName(), schedule.initialDelay, (schedule.delay + " " + schedule.timeUnit));

        }
        service.scheduleWithFixedDelay(task, schedule.initialDelay, schedule.delay, TimeUnit.valueOf(schedule.timeUnit));
    }

    public void shutdown() {
        service.shutdown();
    }
}
