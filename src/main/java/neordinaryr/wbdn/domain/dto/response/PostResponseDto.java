package neordinaryr.wbdn.domain.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PostResponseDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreatePostDto {

        private Long postId;
    }

}
