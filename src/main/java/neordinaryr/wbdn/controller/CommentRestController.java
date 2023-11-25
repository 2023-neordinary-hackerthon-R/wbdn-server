package neordinaryr.wbdn.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.CommentConverter;
import neordinaryr.wbdn.converter.ReplyConverter;
import neordinaryr.wbdn.domain.Comment;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Reply;
import neordinaryr.wbdn.domain.dto.request.CommentRequestDto;
import neordinaryr.wbdn.domain.dto.request.ReplyRequestDto;
import neordinaryr.wbdn.domain.dto.response.CommentResponseDto;
import neordinaryr.wbdn.domain.dto.response.ReplyResponseDto;
import neordinaryr.wbdn.global.apiPayload.BaseCode;
import neordinaryr.wbdn.global.apiPayload.BaseResponse;
import neordinaryr.wbdn.global.apiPayload.SuccessCode;
import neordinaryr.wbdn.service.CommentService;
import neordinaryr.wbdn.service.ReplyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class CommentRestController {
    private final CommentService commentService;
    private final ReplyService replyService;

    /* Comment 관련 엔드포인트 */
    @PostMapping("/posts/{postId}/comments")
    public BaseResponse<CommentResponseDto.SaveCommentDto> postComment(@PathVariable("postId") Long postId,
                                                        @RequestBody @Valid CommentRequestDto.SaveCommentDto dto,
                                                        Member member) {
        Comment comment = commentService.save(postId, dto, member);
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_CREATED, CommentConverter.toSaveCommentDto(comment));
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId,
                              Member member) {
        commentService.delete(commentId, member);
    }

    /* Reply 관련 엔드포인트 */
    @PostMapping("/comments/{commentId}/replies")
    public BaseResponse<ReplyResponseDto.SaveReplyDto> postReply(@PathVariable("commentId") Long commentId,
                                                                 @RequestBody @Valid ReplyRequestDto.SaveReplyDto dto,
                                                                 Member member) {
        Reply reply = replyService.save(commentId, dto, member);
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_CREATED, ReplyConverter.toSaveReplyDto(reply));
    }

    @GetMapping("/comments/{commentId}/replies")
    public BaseResponse<ReplyResponseDto.GetRepliesDto> getReplies(@PathVariable("commentId") Long commentId) {
        List<Reply> replies = commentService.findRepliesById(commentId);
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_OK, ReplyConverter.toGetRepliesDto(replies));
    }

    @DeleteMapping("/replies/{replyId}")
    public void deleteReply(@PathVariable("replyId") Long replyId,
                            Member member) {
        replyService.delete(replyId, member);
    }
}
