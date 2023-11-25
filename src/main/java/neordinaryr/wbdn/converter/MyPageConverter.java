package neordinaryr.wbdn.converter;

import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.dto.response.MyPageResponseDto;

public class MyPageConverter {

    private MyPageConverter() {
    }

    public static MyPageResponseDto.GetMyPageDto toGetMyPageDto(Member member) {
        return MyPageResponseDto.GetMyPageDto.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
    }

}
