package aivlecloudnative.domain;

import aivlecloudnative.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class UserSignedUp extends AbstractEvent {

    private Long userId;
    private String email;
    private String userName;
    private String message;
    private Boolean isKt;

    public UserSignedUp(User aggregate) {
        super(aggregate);

        // ✅ 필드 값 초기화
        this.userId = aggregate.getId();
        this.email = aggregate.getEmail();
        this.userName = aggregate.getUserName();
        this.isKt = aggregate.getIsKt();
    }

    public UserSignedUp() {
        super();
    }
}
//>>> DDD / Domain Event
