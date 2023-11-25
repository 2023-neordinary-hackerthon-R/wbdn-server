package neordinaryr.wbdn.converter;

import java.util.List;
import neordinaryr.wbdn.domain.Reply;
import neordinaryr.wbdn.domain.dto.request.ReplyRequestDto;
import neordinaryr.wbdn.domain.dto.response.ReplyResponseDto;

public class ReplyConverter {

    private ReplyConverter() {
    }

    public static ReplyResponseDto.SaveReplyDto toSaveReplyDto(Reply reply) {
        return ReplyResponseDto.SaveReplyDto.builder()
                .replyId(reply.getId())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    public static ReplyResponseDto.GetRepliesDto toGetRepliesDto(List<Reply> replies) {
        return ReplyResponseDto.GetRepliesDto.builder()
                .replies(replies.stream()
                        .map(ReplyConverter::toGetReplyDto)
                        .toList())
                .build();
    }

    public static ReplyResponseDto.GetReplyDto toGetReplyDto(Reply reply) {
        return ReplyResponseDto.GetReplyDto.builder()
                .replyId(reply.getId())
                .nickname(reply.getMember().getNickname())
                .content(reply.getContents())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    public static Reply toReply(ReplyRequestDto.SaveReplyDto dto) {
        return Reply.builder()
                .contents(dto.getContents())
                .build();
    }

}
