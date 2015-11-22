package data.visualization.domain.util;

import java.util.Date;

public class TimeUtil {

    public long getCurrentDateAsLong() {
        return new Date().getTime();
    }

}
