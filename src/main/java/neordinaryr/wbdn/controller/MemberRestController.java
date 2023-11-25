package neordinaryr.wbdn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.MemberConverter;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.dto.request.MemberRequestDto;
import neordinaryr.wbdn.domain.dto.response.MemberResponseDto;
import neordinaryr.wbdn.global.apiPayload.BaseResponse;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.apiPayload.SuccessCode;
import neordinaryr.wbdn.repository.MemberRepository;
import neordinaryr.wbdn.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "로그인 및 회원 가입", description = "로그인 및 회원 가입 관련 API")
@RequestMapping("/api")
public class MemberRestController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @Operation(summary = "회원 가입", description = "회원 가입입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원 가입 성공",
                    content = @Content(schema = @Schema(implementation = MemberResponseDto.SignUpDto.class))),
            @ApiResponse(responseCode = "400", description = "회원 가입 실패")
    })
    @Parameter(name = "loginId", description = "로그인 ID", required = true)
    @Parameter(name = "password", description = "패스워드", required = true)
    @Parameter(name = "nickname", description = "닉네임", required = true,
            schema = @Schema(implementation = MemberRequestDto.SignUpDto.class))
    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponse<MemberResponseDto.SignUpDto>> signUp(@RequestBody @Valid MemberRequestDto.SignUpDto request) {
        Member member = memberService.saveMember(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.onSuccess(SuccessCode.SUCCESS_CREATED, MemberConverter.toSignUpDto(member)));
    }

    @Operation(summary = "로그인", description = "로그인입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = MemberResponseDto.SignInDto.class))),
    })
    @Parameter(name = "loginId", description = "로그인 ID", required = true)
    @Parameter(name = "password", description = "패스워드", required = true,
            schema = @Schema(implementation = MemberRequestDto.SignInDto.class))
    @PostMapping("/sign-in")
    private ResponseEntity<BaseResponse<MemberResponseDto.SignInDto>> signIn(@RequestBody @Valid MemberRequestDto.SignInDto request) {
        String accessToken = memberService.signIn(request);

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.onSuccess(MemberConverter.toSignInDto(accessToken)));

    }

    @Operation(summary = "로그인 아이디 중복 체크", description = "로그인 아이디 중복 체크입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "중복되는 아이디입니다."),
            @ApiResponse(responseCode = "200", description = "중복되지 않는 아이디입니다."),
    })
    @Parameter(name = "loginId", description = "로그인 아이디", required = true,
            schema = @Schema(implementation = MemberRequestDto.CheckLoginIdDuplicate.class))
    @PostMapping("/check-login-id")
    private ResponseEntity<BaseResponse<String>> loginIdCheck(@RequestBody @Valid MemberRequestDto.CheckLoginIdDuplicate request) {
        if (memberService.checkLoginIdDuplicate(request.getLoginId())) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(BaseResponse.onFailure(ErrorCode.DUPLICATE_LOGIN_ID));
        }

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.onSuccess("중복되지 않는 아이디입니다."));
    }

    @Operation(summary = "닉네임 중복 체크", description = "닉네임 중복 체크입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "중복되는 닉네임입니다."),
            @ApiResponse(responseCode = "200", description = "중복되지 않는 닉네임입니다."),
    })
    @Parameter(name = "nickname", description = "닉네임", required = true,
            schema = @Schema(implementation = MemberRequestDto.CheckNicknameDuplicate.class))

    @PostMapping("/check-username")
    private ResponseEntity<BaseResponse<String>> nicknameCheck(@RequestBody @Valid MemberRequestDto.CheckNicknameDuplicate request) {
        if (memberService.checkNicknameDuplicate(request.getNickname())) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(BaseResponse.onFailure(ErrorCode.DUPLICATE_NICKNAME));
        }

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.onSuccess("중복되지 않는 닉네임입니다."));

    }


}
