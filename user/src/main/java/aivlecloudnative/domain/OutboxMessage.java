package aivlecloudnative.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "outbox")
@Data
@NoArgsConstructor
public class OutboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이벤트의 고유 식별자 (멱등성 처리에 유용)
    private String eventId;

    // 이벤트 타입 (예: "AccessRequestedAsSubscriber", "AccessRequestedWithPoints")
    private String eventType;

    // 이벤트 페이로드 (JSON 문자열로 저장)
    @Column(columnDefinition = "TEXT")
    private String payload;

    // 발행 상태 (READY, PUBLISHED, FAILED 등)
    @Enumerated(EnumType.STRING)
    private PublishStatus status;

    // 생성 시간
    private LocalDateTime createdAt;

    // 발행 시도 시간
    private LocalDateTime publishedAt;

    public enum PublishStatus {
        READY, PUBLISHED, FAILED
    }

    // 생성자 (필요에 따라 추가)
    public OutboxMessage(String eventId, String eventType, String payload) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.payload = payload;
        this.status = PublishStatus.READY;
        this.createdAt = LocalDateTime.now();
    }
}