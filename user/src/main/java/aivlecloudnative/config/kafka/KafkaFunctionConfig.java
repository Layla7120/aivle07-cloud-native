package aivlecloudnative.config.kafka;

import aivlecloudnative.domain.BookViewed;
import aivlecloudnative.domain.User;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaFunctionConfig {

    @Bean
    public Consumer<BookViewed> bookViewedConsumer() {
        return bookViewed -> {
            System.out.println("📘 Book Viewed: " + bookViewed);
            User.updateBookRead(bookViewed);
        };
    }

    @Bean
    public Supplier<String> bookViewedProducer() {
        return () -> {
            // 예시: outbound 메시지 발송 로직
            return "{\"type\":\"BookViewed\", \"value\":\"test\"}";
        };
    }
}
