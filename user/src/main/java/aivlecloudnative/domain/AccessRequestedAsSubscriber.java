package aivlecloudnative.domain;

import aivlecloudnative.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class AccessRequestedAsSubscriber extends AbstractEvent {

    private Long userId;
    private Long bookId;

    public AccessRequestedAsSubscriber(User user, Long bookId) {
        super(user);
        this.userId = user.getId();
        this.bookId = bookId;
    }

    public AccessRequestedAsSubscriber() {
        super();
    }
}
//>>> DDD / Domain Event
