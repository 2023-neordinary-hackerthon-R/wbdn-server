package neordinaryr.wbdn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.PostConverter;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.dto.request.PostRequestDto;
import neordinaryr.wbdn.domain.dto.response.PostListDto.PostListMapDto;
import neordinaryr.wbdn.domain.dto.response.PostResponseDto;
import neordinaryr.wbdn.global.apiPayload.BaseResponse;
import neordinaryr.wbdn.global.apiPayload.SuccessCode;
import neordinaryr.wbdn.security.handler.annotation.ExtractMember;
import neordinaryr.wbdn.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "게시글 관련 API")
public class PostRestController {

    private final PostService postService;

    @Operation(summary = "게시글 등록 API", description = "게시글을 등록합니다. 사진은 무조건 포함되어야 합니다.")
    @ApiResponse(responseCode = "201")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponse<PostResponseDto.CreatePostDto>> createPost(
        @Valid @RequestPart(value = "contents") PostRequestDto.CreatePostDto request,
        @Valid @RequestPart(value = "photo") MultipartFile photo,
        @Parameter(hidden = true) @ExtractMember Member member) throws IOException {
        Post post = postService.createPost(request, photo, member);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(BaseResponse.onSuccess(SuccessCode.SUCCESS_CREATED, PostConverter.toCreatePostDto(post)));
    }

    @Operation(summary = "게시글 삭제 API", description = "게시글을 삭제합니다.")
    @ApiResponse(responseCode = "200")
    @DeleteMapping("/{postId}")
    public ResponseEntity<BaseResponse<Void>> deletePost(@Parameter(name = "postId", description = "삭제할 게시글 id") @PathVariable("postId") Long postId,
        @Parameter(hidden = true) @ExtractMember Member member) {
        postService.deletePost(postId, member);

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.onSuccess(null));
    }

    @Operation(summary = "게시글 지도 조회 API", description = "지도에서 게시글 조회합니다.")
    @ApiResponse(responseCode = "200")
    @GetMapping("/maps")
    public ResponseEntity<BaseResponse<List<PostListMapDto>>> getPostsOnMap(
        @Parameter(name = "currentLat", description = "현재 위치 위도") @RequestParam(name = "currentLat") Double currentLat,
        @Parameter(name = "currentLon", description = "현재 위도 경도") @RequestParam(name = "currentLon") Double currentLon,
        @Parameter(name = "upperRightLat", description = "우상단 위도") @RequestParam(name = "upperRightLat") Double upperRightLat,
        @Parameter(name = "upperRightLon", description = "우상단 경도") @RequestParam(name = "upperRightLon") Double upperRightLon) {

        List<PostListMapDto> result = postService.getPostsOnMap(currentLat, currentLon, upperRightLat, upperRightLon);

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.onSuccess(result));
    }
}
