package neordinaryr.wbdn.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.MemberConverter;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.dto.request.MemberRequestDto;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.exception.BaseException;
import neordinaryr.wbdn.repository.MemberRepository;
import neordinaryr.wbdn.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 사용자 여부, 패스워드 디코딩 비교

    //loginId 중복 체크
    public boolean checkLoginIdDuplicate(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

    //닉네임 중복 체크
    public boolean checkNicknameDuplicate(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    //회원가입
    public void join(MemberRequestDto.SignUpDto joinRequest) {
        memberRepository.save(joinRequest.toEntity(encoder.encode(joinRequest.getPassword())));
    }

    public String signIn(MemberRequestDto.SignInDto request) {
        Member member = memberRepository.findByLoginId(request.getLoginId()).orElseThrow(() -> BaseException.of(ErrorCode.INVALID_LOGIN_REQUEST));

        // 암호화된 password를 디코딩한 값과 입력한 패스워드 값이 다르면 null 반환
        if (!encoder.matches(request.getPassword(), member.getPassword())) {
            throw BaseException.of(ErrorCode.INVALID_LOGIN_REQUEST);
        }

        String accessToken = jwtTokenProvider.createAccessToken(member.getId());

        return accessToken;
    }


    @Transactional
    public Member saveMember(MemberRequestDto.SignUpDto request) {
        if (checkLoginIdDuplicate(request.getLoginId())) {
            throw BaseException.of(ErrorCode.DUPLICATE_LOGIN_ID);
        }

        Member member = memberRepository.save(MemberConverter.toMember(request));

        return member;
    }

}
