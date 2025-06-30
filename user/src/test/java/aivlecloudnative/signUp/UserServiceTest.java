package aivlecloudnative.signUp;

import aivlecloudnative.application.UserService;
import aivlecloudnative.domain.RequestSubscriptionCommand;
import aivlecloudnative.domain.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("존재하지 않는 userId로 요청 시 예외 발생")
    void requestSubscription_shouldThrowException_ifUserNotFound() {
        // given
        Long invalidUserId = 999L;
        RequestSubscriptionCommand command = new RequestSubscriptionCommand();
        command.setUserId(invalidUserId);

        Mockito.when(userRepository.findById(invalidUserId))
                .thenReturn(Optional.empty());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> userService.requestSubscription(command));
        Mockito.verify(userRepository).findById(invalidUserId);
    }
}
