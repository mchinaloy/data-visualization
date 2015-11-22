package data.visualization.domain.formatter;

import data.visualization.domain.util.TimeUtil;
import io.vertx.core.json.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChartProcessorUTest {

    @Mock private TimeUtil timeUtil;
    @InjectMocks private ChartProcessor chartProcessor = new ChartProcessor();

    @Test
    public void formatDataShouldReturnCorrectFormat() {
        // setup
        String testData = "01011000";

        long currentDate = 12345;

        JsonObject valuesOne = new JsonObject();
        valuesOne.put("time", currentDate);
        valuesOne.put("y", 5);

        JsonObject valuesTwo = new JsonObject();
        valuesTwo.put("time", currentDate);
        valuesTwo.put("y", 3);

        JsonObject expectedResult = new JsonObject();
        expectedResult.put("layer1", valuesOne);
        expectedResult.put("layer2", valuesTwo);

        when(timeUtil.getCurrentDateAsLong()).thenReturn(currentDate);

        // act
        JsonObject actualResult = chartProcessor.formatData(testData);

        // assert
        assertEquals(expectedResult, actualResult);
    }

}
