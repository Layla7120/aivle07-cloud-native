package aivlecloudnative.infra;

import aivlecloudnative.domain.OutboxMessage;
import aivlecloudnative.domain.OutboxMessage.PublishStatus;
import aivlecloudnative.domain.OutboxMessageRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPublisher {

    private final OutboxMessageRepository outboxRepo;
    private final StreamBridge streamBridge;

    @Scheduled(fixedDelay = 3000)
    @Transactional
    public void publishEvents() {
        log.info("🟢 OutboxPublisher 스케줄러 실행됨");
        List<OutboxMessage> messages = outboxRepo.findByStatusOrderByCreatedAtAsc(OutboxMessage.PublishStatus.READY);

        for (OutboxMessage msg : messages) {
            try {
                Message<String> message = MessageBuilder
                        .withPayload(msg.getPayload())
                        .setHeader("type", msg.getEventType())
                        .build();

                boolean success = streamBridge.send("event-out", message);
                if (success) {
                    msg.setStatus(PublishStatus.PUBLISHED);
                } else {
                    msg.setStatus(OutboxMessage.PublishStatus.FAILED);
                }
                log.info("✅ OutboxPublisher 스케줄러 실행됨. 메시지 개수: {}", messages.size());
            } catch (Exception e) {
                msg.setStatus(OutboxMessage.PublishStatus.FAILED);
                log.error("Outbox publish failed: {}", e.getMessage(), e);
            }
        }
    }
}
