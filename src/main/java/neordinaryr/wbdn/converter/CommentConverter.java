package neordinaryr.wbdn.converter;

import neordinaryr.wbdn.domain.Comment;
import neordinaryr.wbdn.domain.dto.request.CommentRequestDto;

public class CommentConverter {

    private CommentConverter() {
    }

    public static Comment toComment(CommentRequestDto.SaveCommentDto dto) {
        return Comment.builder()
                .id(dto.getPostId())
                .contents(dto.getContents())
                .build();
    }
}
