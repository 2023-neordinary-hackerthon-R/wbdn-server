package neordinaryr.wbdn.domain.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentResponseDto {

    private CommentResponseDto() {
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SaveCommentDto {
        Long commentId;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetCommentDto {
        Long commentId;
        String content;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetCommentsDto {
        List<GetCommentDto> comments;
    }

}
