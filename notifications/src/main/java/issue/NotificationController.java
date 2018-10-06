package issue;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/rest")
@Validated
public class NotificationController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }


    @Post(produces = MediaType.TEXT_PLAIN)
    public String send(@Body Notification notification) {
        log.debug("Received request to send message");
        service.send(notification);
        return "Notification send";
    }

}
