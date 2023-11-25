package neordinaryr.wbdn.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import neordinaryr.wbdn.domain.Member;

public class MemberRequestDto {

    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Setter
    public static class SignUpDto {

        @NotBlank(message = "아이디를 입력하세요.")
        private String loginId;

        @NotBlank(message = "비밀번호를 입력하세요.")
        private String password;

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
