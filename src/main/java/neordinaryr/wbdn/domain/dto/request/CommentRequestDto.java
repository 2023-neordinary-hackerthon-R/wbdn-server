package neordinaryr.wbdn.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Value;

@Value
public class CommentRequestDto {

    @Getter
    public static class SaveCommentDto {
        @NotNull
        private Long postId;
        @NotBlank
        private String contents;
    }

    @Getter
    public static class SearchCommentDto {
        @NotNull
        private Long postId;
    }

    @Getter
    public static class DeleteCommentDto {
        @NotNull
        private Long commentId;
    }
}
