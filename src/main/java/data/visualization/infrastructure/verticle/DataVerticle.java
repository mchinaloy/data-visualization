package data.visualization.infrastructure.verticle;

import data.visualization.domain.formatter.DataProcessor;
import data.visualization.domain.DataRepository;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DataVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(DataVerticle.class);

    private static final int INTERVAL = 1;
    private static final int INITIAL_DELAY = 0;

    private final String eventBusName;
    private final DataProcessor dataProcessor;
    private final DataRepository dataRepository;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public DataVerticle(String eventBusName, DataProcessor dataProcessor, DataRepository dataRepository) {
        this.eventBusName = eventBusName;
        this.dataProcessor = dataProcessor;
        this.dataRepository = dataRepository;
    }

    @Override
    public void start() throws Exception {
        LOG.info("Starting DataVerticle");
        DataReader dataReader = new DataReader();
        executorService.scheduleAtFixedRate(dataReader, INITIAL_DELAY, INTERVAL, TimeUnit.SECONDS);
    }

    private class DataReader implements Runnable {
        @Override
        public void run() {
            JsonObject jsonObject = dataProcessor.formatData(dataRepository.loadData());
            vertx.eventBus().publish(eventBusName, jsonObject);
        }
    }
}
