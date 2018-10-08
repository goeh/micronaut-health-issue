package issue;

import io.micronaut.health.HealthStatus;
import io.micronaut.management.health.indicator.AbstractHealthIndicator;

import javax.inject.Singleton;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Goran Ehrsson
 * @since 1.0
 */
@Singleton
public class MyHealthIndicator extends AbstractHealthIndicator<Map<String, Object>> {

    @Override
    protected Map<String, Object> getHealthInformation() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("message", "hello world");
        return result;
    }

    @Override
    protected String getName() {
        return "hello";
    }

    public void setStatus(HealthStatus status) {
        healthStatus = status;
    }

    public HealthStatus getStatus() {
        return healthStatus;
    }
}
