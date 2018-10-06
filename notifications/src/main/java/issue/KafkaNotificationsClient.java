package issue;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;

@KafkaClient
public interface KafkaNotificationsClient {

    @Topic("notifications")
    void send(@KafkaKey String key, @Body Object payload);
}
