package aivlecloudnative.infra;

import aivlecloudnative.domain.User;
import aivlecloudnative.domain.BookViewed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PolicyHandler {

    @Bean
    public Consumer<BookViewed> updateBookRead() {
        return bookViewed -> {
            System.out.println("##### listener UpdateBookRead : " + bookViewed);
            User.updateBookRead(bookViewed);
        };
    }
}
