package neordinaryr.wbdn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.dto.response.PostDetailResDto;
import neordinaryr.wbdn.domain.dto.response.PostLikeResDto;
import neordinaryr.wbdn.domain.dto.response.PostListDto;
import neordinaryr.wbdn.global.apiPayload.BaseResponse;
import neordinaryr.wbdn.service.PostDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostDetailController {

    private PostDetailService postDetailService;

    // post 전체 조회
    @Operation(summary = "post 리스트 전체 조회(추천순)", description = "메인화면 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = PostListDto.PostListResDto.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    @GetMapping("/posts")
    public BaseResponse<PostListDto.PostListResDto> findPostAll(Member member) { // 멤버 필요
        PostListDto.PostListResDto res = postDetailService.findPostAll(member);
        return BaseResponse.onSuccess(res);
    }

    @Operation(summary = "post 상세 조회", description = "상세 post 조회 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = PostDetailResDto.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    @Parameter(name = "postId", description = "post 아이디")
    @GetMapping("/posts/{postId}")
    public BaseResponse<PostDetailResDto> findPostDetail(@PathVariable Long postId, Member member) { // 멤버, postId 필요
        PostDetailResDto res = postDetailService.findPostDetail(postId, member);
        return BaseResponse.onSuccess(res);
    }
}
