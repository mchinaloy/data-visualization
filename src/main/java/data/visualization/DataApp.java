package data.visualization;

import data.visualization.domain.DataRepository;
import data.visualization.infrastructure.verticle.DataVerticle;
import data.visualization.domain.formatter.ChartProcessor;
import data.visualization.domain.formatter.HistogramProcessor;
import data.visualization.infrastructure.verticle.ServerVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AppConfig.class })
public class DataApp implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DataApp.class);

    @Autowired private Vertx vertx;
    @Autowired private DataRepository dataRepository;
    @Autowired private ServerVerticle serverVerticle;
    @Autowired private ChartProcessor chartProcessor;
    @Autowired private HistogramProcessor histogramProcessor;

    @Value("${chart.data.out}") private String chartEventBusName;
    @Value("${histogram.data.out}") private String histogramEventBusName;

    public static void main(String[] args) {
        SpringApplication.run(DataApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("Starting Vertx Application");

        DataVerticle chartDataVerticle = new DataVerticle(chartEventBusName, chartProcessor, dataRepository);
        DataVerticle histogramDataVerticle = new DataVerticle(histogramEventBusName, histogramProcessor, dataRepository);

        vertx.deployVerticle(serverVerticle);
        vertx.deployVerticle(chartDataVerticle);
        vertx.deployVerticle(histogramDataVerticle);

        LOG.info("Application Deployed");
    }
}
