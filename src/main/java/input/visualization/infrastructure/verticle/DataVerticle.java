package input.visualization.infrastructure.verticle;

import input.visualization.domain.DataRepository;
import input.visualization.domain.formatter.DataProcessor;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DataVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(DataVerticle.class);

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
        executorService.scheduleAtFixedRate(dataReader, 0, 10, TimeUnit.SECONDS);
    }

    private class DataReader implements Runnable {
        @Override
        public void run() {
            try {
                for (String line : dataRepository.loadData()) {
                    JsonObject jsonObject = dataProcessor.formatData(line);
                    vertx.eventBus().publish(eventBusName, jsonObject);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
