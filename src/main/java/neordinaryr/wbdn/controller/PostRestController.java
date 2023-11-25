package neordinaryr.wbdn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.PostConverter;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.dto.request.PostRequestDto;
import neordinaryr.wbdn.domain.dto.response.PostResponseDto;
import neordinaryr.wbdn.global.apiPayload.BaseResponse;
import neordinaryr.wbdn.global.apiPayload.SuccessCode;
import neordinaryr.wbdn.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @Operation(summary = "게시글 등록 API", description = "레시피를 등록합니다. 사진은 무조건 포함되어야 합니다.")
    @ApiResponse(responseCode = "201")
    @Parameters({
        @Parameter(name = "member", hidden = true)
    })
    @PostMapping
    public ResponseEntity<BaseResponse<PostResponseDto.CreatePostDto>> createPost(
        @RequestPart(value = "contents") PostRequestDto.CreatePostDto request,
        @RequestPart(value = "photo") MultipartFile photo,
        Member member) throws IOException {
        
        Post post = this.postService.createPost(request, photo, member);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(BaseResponse.onSuccess(SuccessCode.SUCCESS_CREATED, PostConverter.toCreatePostDto(post)));
    }
}
