package neordinaryr.wbdn.domain.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReplyResponseDto {

    private ReplyResponseDto() {
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SaveReplyDto {
        Long replyId;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetReplyDto {
        Long replyId;
        String content;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetRepliesDto {
        List<GetReplyDto> replies;
    }
}
