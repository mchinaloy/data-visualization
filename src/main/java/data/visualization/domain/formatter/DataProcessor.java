package data.visualization.domain.formatter;

import io.vertx.core.json.JsonObject;

public interface DataProcessor {

    JsonObject formatData(String line);

}
