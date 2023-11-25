package neordinaryr.wbdn.domain.dto.response;

import java.time.LocalDateTime;
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

}
