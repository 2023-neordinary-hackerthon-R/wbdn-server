package neordinaryr.wbdn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "댓글 및 답글", description = "댓글과 답글 관련 API")
@RequestMapping("/api")
public class CommentRestController {
    private final CommentService commentService;
    private final ReplyService replyService;

    /* Comment 관련 엔드포인트 */
    @Operation(summary = "댓글 작성", description = "댓글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "댓글 작성 성공"),
            @ApiResponse(responseCode = "400", description = "댓글 작성 실패")
    })
    @Parameter(name = "postId", description = "게시글 ID", required = true)
    @PostMapping("/posts/{postId}/comments")
    public BaseResponse<CommentResponseDto.SaveCommentDto> postComment(@PathVariable("postId") Long postId,
                                                                       @RequestBody @Valid CommentRequestDto.SaveCommentDto dto,
                                                                       @Parameter(hidden = true) Member member) {
        Comment comment = commentService.save(postId, dto, member);
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_CREATED, CommentConverter.toSaveCommentDto(comment));
    }

    @Operation(summary = "게시글 댓글 리스트 조회", description = "해당하는 게시글의 댓글 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 리스트 조회 성공"),
            @ApiResponse(responseCode = "400", description = "댓글 리스트 조회 실패")
    })
    @Parameter(name = "postId", description = "게시글 ID", required = true)
    @GetMapping("/posts/{postId}/comments")
    public BaseResponse<CommentResponseDto.GetCommentsDto> getComments(@PathVariable("postId") Long postId) {
        List<Comment> comments = commentService.findCommentsByPostId(postId);
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_OK, CommentConverter.toGetCommentsDto(comments));
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "댓글 삭제 실패")
    })
    @Parameter(name = "commentId", description = "댓글 ID", required = true)
    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId,
                              @Parameter(hidden = true) Member member) {
        commentService.delete(commentId, member);
    }

    /* Reply 관련 엔드포인트 */
    @Operation(summary = "답글 작성", description = "답글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "답글 작성 성공"),
            @ApiResponse(responseCode = "400", description = "답글 작성 실패")
    })
    @Parameter(name = "commentId", description = "댓글 ID", required = true)
    @Parameter(name = "dto", description = "답글 작성 정보", required = true,
            schema = @Schema(implementation = ReplyRequestDto.SaveReplyDto.class))
    @PostMapping("/comments/{commentId}/replies")
    public BaseResponse<ReplyResponseDto.SaveReplyDto> postReply(@PathVariable("commentId") Long commentId,
                                                                 @RequestBody @Valid ReplyRequestDto.SaveReplyDto dto,
                                                                 @Parameter(hidden = true) Member member) {
        Reply reply = replyService.save(commentId, dto, member);
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_CREATED, ReplyConverter.toSaveReplyDto(reply));
    }

    @Operation(summary = "댓글 답글 리스트 조회", description = "해당하는 댓글의 답글 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "답글 리스트 조회 성공"),
            @ApiResponse(responseCode = "400", description = "답글 리스트 조회 실패")
    })
    @Parameter(name = "commentId", description = "댓글 ID", required = true)
    @Parameter(name = "dto", description = "답글 리스트 조회 정보", required = true,
            schema = @Schema(implementation = ReplyResponseDto.GetRepliesDto.class))
    @GetMapping("/comments/{commentId}/replies")
    public BaseResponse<ReplyResponseDto.GetRepliesDto> getReplies(@PathVariable("commentId") Long commentId) {
        List<Reply> replies = commentService.findRepliesById(commentId);
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_OK, ReplyConverter.toGetRepliesDto(replies));
    }

    @Operation(summary = "답글 삭제", description = "답글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "답글 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "답글 삭제 실패")
    })
    @Parameter(name = "replyId", description = "답글 ID", required = true)
    @DeleteMapping("/replies/{replyId}")
    public void deleteReply(@PathVariable("replyId") Long replyId,
                            @Parameter(hidden = true) Member member) {
        replyService.delete(replyId, member);
    }
}
