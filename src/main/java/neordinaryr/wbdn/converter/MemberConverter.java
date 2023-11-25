package neordinaryr.wbdn.converter;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.dto.request.MemberRequestDto;
import neordinaryr.wbdn.domain.dto.response.MemberResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberConverter {

    private final PasswordEncoder passwordEncoder;
    private static PasswordEncoder staticPasswordEncoder;

    @PostConstruct
    void init() {
        staticPasswordEncoder = this.passwordEncoder;
    }

    // Convert LoginRequest to Member
    public static MemberResponseDto.SignInDto toSignInDto(String accessKey) {
        return MemberResponseDto.SignInDto.builder().accessKey(accessKey).build();
    }

    public static MemberResponseDto.SignUpDto toSignUpDto(Member member) {
        return MemberResponseDto.SignUpDto.builder().memberId(member.getId()).build();
    }

    public static Member toMember(MemberRequestDto.SignUpDto signUpDto) {
        return Member.builder()
                     .loginId(signUpDto.getLoginId())
                     .password(staticPasswordEncoder.encode(signUpDto.getPassword()))
                     .nickname(signUpDto.getNickname())
                     .build();
    }
}
