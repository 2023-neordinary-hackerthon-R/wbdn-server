package neordinaryr.wbdn.domain.dto.response;

import lombok.*;

public class MemberResponseDto {
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignInDto {
        private String accessKey;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpDto {
        private Long memberId;
    }

}