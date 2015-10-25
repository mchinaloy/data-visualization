package data.visualization;

import data.visualization.domain.DataRepository;
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
    public ServerVerticle serverVerticle() {
        return new ServerVerticle();
    }

    @Bean
    public DataRepository dataRepository() {
        return new FileRepository();
    }

}
