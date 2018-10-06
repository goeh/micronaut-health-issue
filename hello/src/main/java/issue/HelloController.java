package issue;

import io.micronaut.health.HealthStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

@Controller("/")
public class HelloController {

    private final MyKafkaClient kafkaClient;
    private final MyHealthIndicator healthIndicator;

    public HelloController(MyKafkaClient kafkaClient, MyHealthIndicator healthIndicator) {
        this.kafkaClient = kafkaClient;
        this.healthIndicator = healthIndicator;
    }

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "Status is " + healthIndicator.getStatus();
    }

    @Post("/{status}")
    public String setStatus(String status) {
        healthIndicator.setStatus(new HealthStatus(status.toUpperCase()));
        return "Status set to " + healthIndicator.getStatus();
    }

    @Post("/send")
    @Produces(MediaType.TEXT_PLAIN)
    public String send() {
        kafkaClient.send(String.valueOf(System.currentTimeMillis()), new Notification("username", "Hello", "Hello World!"));
        return "\"Hello\" sent to Kafka topic 'notifications'\n";
    }
}
