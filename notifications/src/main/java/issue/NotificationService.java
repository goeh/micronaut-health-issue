package issue;

import io.micronaut.validation.Validated;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

@Singleton
@Validated
public class NotificationService {

    private final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final KafkaNotificationsClient notificationsClient;

    public NotificationService(KafkaNotificationsClient notificationsClient) {
        this.notificationsClient = notificationsClient;
    }

    public Single<Notification> send(@NotNull Notification notification) {
        notificationsClient.send(String.valueOf(notification.hashCode()), notification);
        log.debug("Message sent to Kafka {}", notification);
        return Single.just(notification);
    }
}
