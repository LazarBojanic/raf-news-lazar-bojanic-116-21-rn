package rs.raf.rafnews.logging;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.apache.commons.lang3.time.StopWatch;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Interceptor
@LogEndpoint
public class LogEndpointInterceptor {
    @AroundInvoke
    public Object logEndpoint(InvocationContext context) throws Exception {
        var startTime = Timestamp.from(Instant.now());
        Object result = context.proceed();
        var endTime = Timestamp.from(Instant.now());
        System.out.println("Method: " + context.getMethod().getName() + "; Called at: " + startTime + "; Completed at: " + endTime + "; Time of execution: " + getTimestampDifference(startTime, endTime));
        return result;
    }
    private String getTimestampDifference(Timestamp startTime, Timestamp endTime) {
        long millisecondsDiff = endTime.getTime() - startTime.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(millisecondsDiff);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millisecondsDiff) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millisecondsDiff) % 60;
        long milliseconds = millisecondsDiff % 1000;
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
    }
}
