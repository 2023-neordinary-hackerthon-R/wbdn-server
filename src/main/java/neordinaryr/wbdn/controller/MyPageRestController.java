package neordinaryr.wbdn.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.MyPageConverter;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.dto.response.MyPageResponseDto;
import neordinaryr.wbdn.global.apiPayload.BaseResponse;
import neordinaryr.wbdn.global.apiPayload.SuccessCode;
import neordinaryr.wbdn.service.MyPageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/me")
public class MyPageRestController {

    private final MyPageService myPageService;

    @GetMapping("/profiles")
    public BaseResponse<MyPageResponseDto.GetMyPageDto> getMyPage(@Parameter(hidden = true) Member member) {
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_OK, MyPageConverter.toGetMyPageDto(member));
    }

    @GetMapping("/posts")
    public BaseResponse<MyPageResponseDto.GetMyPagePostsDto> getMyPagePosts(@Parameter(hidden = true) Member member) {
        return BaseResponse.onSuccess(SuccessCode.SUCCESS_OK, MyPageConverter.toGetMyPagePostsDto(member));
    }

}
