package neordinaryr.wbdn.converter;

import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.dto.request.MemberRequestDto;
import neordinaryr.wbdn.domain.dto.response.MemberResponseDto;

public class MemberConverter {
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
                .password(signUpDto.getPassword())
                .nickname(signUpDto.getNickname())
                .build();
    }
}
