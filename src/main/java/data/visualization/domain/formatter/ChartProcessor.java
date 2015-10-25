package data.visualization.domain.formatter;

import io.vertx.core.json.JsonObject;
import org.springframework.util.StringUtils;

import java.util.Date;

public class ChartProcessor implements DataProcessor {

    public JsonObject formatData(String line) {
        int numZero = StringUtils.countOccurrencesOf(line, "0");
        int numOne = StringUtils.countOccurrencesOf(line, "1");

        long date = new Date().getTime();

        JsonObject valuesZero = new JsonObject();
        valuesZero.put("time", date);
        valuesZero.put("y", numZero);

        JsonObject valuesOne = new JsonObject();
        valuesOne.put("time", date);
        valuesOne.put("y", numOne);

        JsonObject finalResult = new JsonObject();
        finalResult.put("layer1", valuesZero);
        finalResult.put("layer2", valuesOne);

        return finalResult;
    }

}
