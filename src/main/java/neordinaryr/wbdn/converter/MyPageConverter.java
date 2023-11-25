package neordinaryr.wbdn.converter;

import java.util.List;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Post;
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

    public static MyPageResponseDto.GetMyPagePostsDto toGetMyPagePostsDto(Member member) {
        List<Post> posts = member.getPosts();
        return MyPageResponseDto.GetMyPagePostsDto.builder()
                .postList(posts.stream()
                        .map(MyPageConverter::toGetMyPagePostDto)
                        .toList())
                .build();
    }

    public static MyPageResponseDto.GetMyPagePostDto toGetMyPagePostDto(Post post) {
        return MyPageResponseDto.GetMyPagePostDto.builder()
                .postId(post.getId())
                .photoUrl(post.getPhoto().getPhotoUrl())
                .likes(post.getLikes())
                .build();
    }

}
