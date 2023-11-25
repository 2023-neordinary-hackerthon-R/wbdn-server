package neordinaryr.wbdn.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Value;

@Value
public class ReplyRequestDto {

    @Getter
    public static class SaveReplyDto {
        @NotBlank
        private String contents;
    }

}
