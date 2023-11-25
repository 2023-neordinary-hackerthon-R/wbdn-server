package neordinaryr.wbdn.controller;

import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.MyPageConverter;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.PostLike;
import neordinaryr.wbdn.domain.dto.response.MyPageResponseDto;
import neordinaryr.wbdn.global.apiPayload.BaseResponse;
import neordinaryr.wbdn.global.apiPayload.SuccessCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/me")
public class MyPageRestController {

    @GetMapping("/profiles")
    public BaseResponse<MyPageResponseDto.GetMyPageDto> getMyPage(@Parameter(hidden = true) Member member) {
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_OK, MyPageConverter.toGetMyPageDto(member));
    }

    @GetMapping("/posts")
    public BaseResponse<MyPageResponseDto.GetMyPagePostsDto> getMyPagePosts(@Parameter(hidden = true) Member member) {
        List<Post> posts = member.getPosts();
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_OK, MyPageConverter.toGetMyPagePostsDto(posts));
    }

    @GetMapping("/likes")
    public BaseResponse<MyPageResponseDto.GetMyPagePostsDto> getMyPageLikes(@Parameter(hidden = true) Member member) {
        List<Post> posts = member.getPostLikes().stream()
                .map(PostLike::getPost)
                .toList();
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_OK, MyPageConverter.toGetMyPagePostsDto(posts));
    }

}
