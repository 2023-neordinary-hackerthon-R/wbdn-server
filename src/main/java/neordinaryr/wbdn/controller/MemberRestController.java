package neordinaryr.wbdn.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.MemberConverter;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.dto.request.MemberRequestDto;
import neordinaryr.wbdn.domain.dto.response.MemberResponseDto;
import neordinaryr.wbdn.global.apiPayload.BaseResponse;
import neordinaryr.wbdn.global.apiPayload.SuccessCode;
import neordinaryr.wbdn.repository.MemberRepository;
import neordinaryr.wbdn.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberRestController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // @Autowired
    // private String secretKey;
    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponse<MemberResponseDto.SignUpDto>> signUp(@RequestBody @Valid MemberRequestDto.SignUpDto request) {
        Member member = memberService.saveMember(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.onSuccess(SuccessCode.SUCCESS_CREATED, MemberConverter.toSignUpDto(member)));
    }

    @PostMapping("/sign-in")
    private ResponseEntity<BaseResponse<MemberResponseDto.SignInDto>> signIn(@RequestBody @Valid MemberRequestDto.SignInDto request) {
        String accessToken = memberService.signIn(request);

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.onSuccess(MemberConverter.toSignInDto(accessToken)));

    }
}
