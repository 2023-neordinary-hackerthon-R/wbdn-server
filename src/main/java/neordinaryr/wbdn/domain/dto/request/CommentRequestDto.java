package neordinaryr.wbdn.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Value;

@Value
public class CommentRequestDto {

    @Getter
    public static class SaveCommentDto {
        @NotBlank
        private String contents;
    }

}
