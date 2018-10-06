package issue;

import io.micronaut.websocket.CloseReason;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerWebSocket("/ws/notifications")
public class NotificationsWebSocket {

    private static final Logger log = LoggerFactory.getLogger(NotificationsWebSocket.class);

    @OnOpen
    public void onOpen(WebSocketSession session) {
        log.debug("WebSocket opened for {}", session);
    }

    @OnMessage
    public void onMessage(Notification notification, WebSocketSession session) {
        log.debug("Received notification! {} from {}", notification, session);
    }

    @OnClose
    public void onClose(CloseReason reason, WebSocketSession session) {
        log.debug("Disconnected from {}", session);
    }
}
