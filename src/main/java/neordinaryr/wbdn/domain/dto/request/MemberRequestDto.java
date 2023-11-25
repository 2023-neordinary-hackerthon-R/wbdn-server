package neordinaryr.wbdn.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import neordinaryr.wbdn.domain.Member;

public class MemberRequestDto {

    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    public static class SignUpDto {

        @NotBlank(message = "아이디를 입력하세요.")
        private String loginId;

        @NotBlank(message = "비밀번호를 입력하세요.")
        private String password;
        private String passwordCheck;

        @NotBlank(message = "닉네임을 입력하세요.")
        private String nickname;

        public Member toEntity(String encodePassword) {
            return Member.builder()
                    .loginId(this.loginId)
                    .password(encodePassword)
                    .nickname(this.nickname)
                    .build();
        }
    }

    @Getter
    public static class SignInDto {
        private String loginId;
        private String password;

    }

}