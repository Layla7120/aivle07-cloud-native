package aivlecloudnative.application;

import aivlecloudnative.domain.AccessRequestedAsSubscriber;
import aivlecloudnative.domain.AccessRequestedWithPoints;
import aivlecloudnative.domain.OutboxMessage;
import aivlecloudnative.domain.OutboxMessageRepository;
import aivlecloudnative.domain.RequestContentAccessCommand;
import aivlecloudnative.domain.RequestSubscriptionCommand;
import aivlecloudnative.domain.SignUpCommand;
import aivlecloudnative.domain.User;
import aivlecloudnative.domain.UserRepository;
import aivlecloudnative.domain.UserSignedUp;
import aivlecloudnative.domain.UserSubscribed;
import aivlecloudnative.infra.AbstractEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OutboxMessageRepository outboxMessageRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public User signUp(SignUpCommand cmd) {
        if (userRepository.existsByEmail(cmd.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        User user = new User();
        user.signUp(cmd);

        saveOutbox(new UserSignedUp(user), "UserSignedUp");

        return userRepository.save(user);
    }

    @Transactional
    public User requestSubscription(RequestSubscriptionCommand cmd) {
        User user = userRepository.findById(cmd.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID: " + cmd.getUserId()));

        user.setHasActiveSubscription(true);

        saveOutbox(new UserSubscribed(user), "UserSubscribed");

        return userRepository.save(user);
    }

    @Transactional
    public User requestContentAccess(RequestContentAccessCommand command) {
        Long userId = command.getUserId();
        Long bookId = command.getBookId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다: " + userId));

        //TODO: bookId가 유효한지 확인

        boolean isKT = user.getIsKt();

        AbstractEvent domainEvent = isKT
                ? new AccessRequestedAsSubscriber(user, bookId)
                : new AccessRequestedWithPoints(user, bookId);

        saveOutbox(domainEvent, domainEvent.getClass().getSimpleName());
        user.addBookToHistory(bookId);

        return userRepository.save(user);
    }

    private void saveOutbox(AbstractEvent event, String eventType) {
        try {
            String payload = objectMapper.writeValueAsString(event);

            OutboxMessage msg = new OutboxMessage(
                    UUID.randomUUID().toString(),
                    eventType,
                    payload
            );
            outboxMessageRepository.save(msg);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("이벤트 JSON 직렬화 실패", e);
        }
    }
}
