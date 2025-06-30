package aivlecloudnative.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpCommand {

    @NotBlank(message = "닉네임을 입력해 주세요")
    private String userName;

    @Email(message = "이메일 형식이 올바르지 않습니다")
    @NotBlank(message = "이메일을 입력해 주세요")
    private String email;

    @NotNull(message = "KT 여부를 선택해 주세요")
    private Boolean isKt;
}
