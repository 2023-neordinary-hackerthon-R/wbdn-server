package neordinaryr.wbdn.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MyPageResponseDto {

    private MyPageResponseDto() {
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetMyPageDto {
        Long memberId;
        String nickname;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetMyPagePostsDto {

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetMyPagePostDto {

    }
}
