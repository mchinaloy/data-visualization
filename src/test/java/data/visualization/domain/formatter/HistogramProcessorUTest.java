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
public class HistogramProcessorUTest {

    @Mock private TimeUtil timeUtil;
    @InjectMocks private HistogramProcessor histogramProcessor = new HistogramProcessor();

    @Test
    public void formatDataShouldReturnCorrectFormat() {
        // setup
        String testData = "01011000";

        long currentDate = 12345;

        when(timeUtil.getCurrentDateAsLong()).thenReturn(currentDate);

        JsonObject histogram = new JsonObject();
        histogram.put("88", 1);

        JsonObject expectedResult = new JsonObject();
        expectedResult.put("time", timeUtil.getCurrentDateAsLong());
        expectedResult.put("histogram", histogram);

        // act
        JsonObject actualResult = histogramProcessor.formatData(testData);

        // assert
        assertEquals(expectedResult, actualResult);
    }

}
