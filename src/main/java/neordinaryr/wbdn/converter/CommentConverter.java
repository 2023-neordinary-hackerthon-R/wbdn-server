package neordinaryr.wbdn.converter;

import java.util.List;
import neordinaryr.wbdn.domain.Comment;
import neordinaryr.wbdn.domain.dto.request.CommentRequestDto;
import neordinaryr.wbdn.domain.dto.response.CommentResponseDto;

public class CommentConverter {

    private CommentConverter() {
    }

    public static CommentResponseDto.SaveCommentDto toSaveCommentDto(Comment comment) {
        return CommentResponseDto.SaveCommentDto.builder()
                .commentId(comment.getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static CommentResponseDto.GetCommentsDto toGetCommentsDto(List<Comment> comments) {
        return CommentResponseDto.GetCommentsDto.builder()
                .comments(comments.stream()
                        .map(CommentConverter::toGetCommentDto)
                        .toList())
                .build();
    }

    public static CommentResponseDto.GetCommentDto toGetCommentDto(Comment comment) {
        return CommentResponseDto.GetCommentDto.builder()
                .commentId(comment.getId())
                .content(comment.getContents())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static Comment toComment(CommentRequestDto.SaveCommentDto dto) {
        return Comment.builder()
                .contents(dto.getContents())
                .build();
    }
}
