package aivlecloudnative.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestContentAccessCommand {

    @NotNull(message = "아이디를 입력해주세요")
    private Long userId;

    @NotNull(message = "책 아이디를 입력해주세요")
    private Long bookId;
}
