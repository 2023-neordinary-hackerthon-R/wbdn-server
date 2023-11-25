package neordinaryr.wbdn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.dto.response.PostLikeResDto;
import neordinaryr.wbdn.global.apiPayload.BaseResponse;
import neordinaryr.wbdn.service.PostLikeServcie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
@Tag(name = "PostLike", description = "게시글 좋아요")
public class PostLikeController {

    private final PostLikeServcie postLikeServcie;

    // 좋아요 저장
    @Operation(summary = "좋아요 저장", description = "좋아요 저장하는 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = PostLikeResDto.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    @Parameter(name = "postId", description = "post 아이디")
    @PostMapping("/{postId}/likes")
    public BaseResponse<PostLikeResDto> saveLike (@PathVariable Long postId, Member member) { // postId랑 member 받기
        PostLikeResDto res = postLikeServcie.saveLike(postId, member);
        return BaseResponse.onSuccess(res);
    }
}
