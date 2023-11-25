package neordinaryr.wbdn.domain.dto.response;

import lombok.*;

import java.util.List;

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
    @AllArgsConstructor
    public static class PostListResDto {
        private Long memberId;
        private String nickname;
        private List<PostListDto.GetPostListDto> postListDtos;
    }

}
