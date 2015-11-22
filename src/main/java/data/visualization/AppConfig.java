package data.visualization;

import data.visualization.domain.DataRepository;
import data.visualization.domain.formatter.ChartProcessor;
import data.visualization.domain.formatter.HistogramProcessor;
import data.visualization.domain.util.TimeUtil;
import data.visualization.infrastructure.persistence.FileRepository;
import data.visualization.infrastructure.verticle.ServerVerticle;
import io.vertx.core.Vertx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Vertx vertx() {
        return Vertx.vertx();
    }

    @Bean
    public ChartProcessor chartProcessor() {
        return new ChartProcessor();
    }

    @Bean
    public HistogramProcessor histogramProcessor() {
        return new HistogramProcessor();
    }

    @Bean
    public ServerVerticle serverVerticle() {
        return new ServerVerticle();
    }

    @Bean
    public TimeUtil timeUtil() {
        return new TimeUtil();
    }

    @Bean
    public DataRepository dataRepository() {
        return new FileRepository();
    }

}
