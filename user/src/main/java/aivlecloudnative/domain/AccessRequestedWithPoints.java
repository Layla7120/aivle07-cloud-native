package aivlecloudnative.domain;

import aivlecloudnative.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class AccessRequestedWithPoints extends AbstractEvent {

    private Long userId;
    private Long bookId;

    public AccessRequestedWithPoints(User user, Long bookId) {
        super(user);
        this.userId = user.getId();
        this.bookId = bookId;
    }

    public AccessRequestedWithPoints() {
        super();
    }
}
//>>> DDD / Domain Event
