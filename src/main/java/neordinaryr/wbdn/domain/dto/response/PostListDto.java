package neordinaryr.wbdn.domain.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostListDto {

    private PostListDto() {
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class GetPostListDto {

        private Long postId;
        private String nickname;
        private String photoUrl;
        private Long likes;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostListMapDto {

        private Long postId;
        private String nickname;
        private String photoUrl;
        private Long likes;
        private Double latitude;
        private Double longitude;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class PostListResDto {

        private Long memberId;
        private String nickname;
        private List<PostListDto.GetPostListDto> postListDtos;
    }

}
