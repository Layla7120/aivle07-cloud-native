package aivlecloudnative.infra;

import aivlecloudnative.domain.UserSignedUp;
import aivlecloudnative.domain.UserSubscribed;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserEventHandler {

    private final AbstractEventPublisher eventPublisher;

    public UserEventHandler(AbstractEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @TransactionalEventListener
    public void handle(UserSignedUp event) {
        eventPublisher.publish("event-out", event, "UserSignedUp");
    }

    @TransactionalEventListener
    public void handle(UserSubscribed event) {
        eventPublisher.publish("event-out", event, "UserSubscribed");
    }
}
