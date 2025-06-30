package aivlecloudnative.domain;

import aivlecloudnative.infra.AbstractEvent;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class UserSubscribed extends AbstractEvent {

    private Long userId;
    private String userName;
    private Boolean hasActiveSubscription;
    private String message;
    private String email;
    private Long subscriptionDueDate;
    private List<Integer> myBookHistory;

    public UserSubscribed(User aggregate) {
        super(aggregate);
    }

    public UserSubscribed() {
        super();
    }
}
//>>> DDD / Domain Event
