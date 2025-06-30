
package aivlecloudnative.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OutboxMessageRepository extends JpaRepository<OutboxMessage, Long> {
    // 아직 발행되지 않은 (READY 상태의) 메시지를 조회
    List<OutboxMessage> findByStatusOrderByCreatedAtAsc(OutboxMessage.PublishStatus status);

    // 중복 이벤트를 방지하기 위한 eventId 조회 (선택 사항)
    Optional<OutboxMessage> findByEventId(String eventId);
}