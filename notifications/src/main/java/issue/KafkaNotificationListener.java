package issue;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.websocket.WebSocketBroadcaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Requires(notEnv = Environment.TEST)
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class KafkaNotificationListener {

    private final Logger log = LoggerFactory.getLogger(KafkaNotificationListener.class);

    private final WebSocketBroadcaster broadcaster;

    public KafkaNotificationListener(WebSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    @Topic("notifications")
    public void onMessage(Notification notification) {
        log.debug("Received notification from Kafka: {}", notification);

        broadcaster.broadcastSync(notification);

        log.debug("Sent notification to WebSocket: {}", notification);
    }

}
