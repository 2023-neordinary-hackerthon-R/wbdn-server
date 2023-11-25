package neordinaryr.wbdn.domain.dto.response;

import java.util.List;
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
        private List<GetMyPagePostDto> postList;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetMyPagePostDto {
        private Long postId;
        private String nickname;
        private String photoUrl;
        private Long likes;
    }
}
