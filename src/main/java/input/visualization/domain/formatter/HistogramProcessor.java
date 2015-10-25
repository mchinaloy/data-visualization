package input.visualization.domain.formatter;

import io.vertx.core.json.JsonObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HistogramProcessor implements DataProcessor {

    public JsonObject formatData(String line) {
        Map<Integer, Integer> results = new HashMap<>();
        String[] splitLine = line.split(" ");

        for (String number : splitLine) {
            int parsedNumber = Integer.parseInt(number, 2);
            Integer result = results.computeIfPresent(parsedNumber, (k, v) -> v = v + 1);
            if (result == null) {
                results.put(parsedNumber, 1);
            }
        }

        JsonObject histogram = new JsonObject();

        for (Map.Entry<Integer, Integer> entry : results.entrySet()) {
            histogram.put(entry.getKey().toString(), entry.getValue());
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.put("time", new Date().getTime());
        jsonObject.put("histogram", histogram);

        return jsonObject;
    }

}
