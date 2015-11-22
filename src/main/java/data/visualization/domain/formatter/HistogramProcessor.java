package data.visualization.domain.formatter;

import data.visualization.domain.util.TimeUtil;
import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class HistogramProcessor implements DataProcessor {

    @Autowired private TimeUtil timeUtil;

    private final Map<Integer, Integer> results = new HashMap<>();

    public JsonObject formatData(String line) {
        Map<Integer, Integer> results = processValues(line);

        JsonObject histogram = new JsonObject();

        for (Map.Entry<Integer, Integer> entry : results.entrySet()) {
            histogram.put(entry.getKey().toString(), entry.getValue());
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.put("time", timeUtil.getCurrentDateAsLong());
        jsonObject.put("histogram", histogram);

        return jsonObject;
    }

    protected Map<Integer, Integer> processValues(String line) {
            int parsedNumber = Integer.parseInt(line, 2);
            Integer result = results.computeIfPresent(parsedNumber, (k, v) -> v = v + 1);
            if (result == null) {
                results.put(parsedNumber, 1);
            }
            if(results.size() >= 50){
                results.clear();
            }
        return results;
    }

}
