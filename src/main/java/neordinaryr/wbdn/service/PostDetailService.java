package neordinaryr.wbdn.service;

import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.PostLike;
import neordinaryr.wbdn.domain.dto.response.PostDetailResDto;
import neordinaryr.wbdn.domain.dto.response.PostListDto;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.exception.BaseException;
import neordinaryr.wbdn.repository.MemberRepository;
import neordinaryr.wbdn.repository.PostLikeRepository;
import neordinaryr.wbdn.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostDetailService {

    private PostRepository postRepository;
    private PostLikeServcie postLikeServcie;
    private GeoCodingService geoCodingService;
    private PostLikeRepository postLikeRepository;

    // 전체 조회
    public PostListDto.PostListResDto findPostAll(Member member) throws BaseException{
        // 해당 유저 닉네임 값 받기
        String nickname = member.getNickname();
        List<Post> findPosts = postRepository.findAll();

        // post 리스트 값 받기
        if (findPosts == null || findPosts.isEmpty())
            throw BaseException.of(ErrorCode.POST_NOT_OWNER);
        List<PostListDto.GetPostListDto> result = findPosts.stream()
                .map(p -> {
                    Long likes = postLikeServcie.likeCount(p.getId());
                    return new PostListDto.GetPostListDto(p.getId(), p.getMember().getNickname(), p.getPhoto().getPhotoUrl(), likes);
                })
                .collect(Collectors.toList());

        return new PostListDto.PostListResDto(member.getId(), nickname, result);
    }

    // 해당 Post 상세 조회
    public PostDetailResDto findPostDetail(Long postId, Member member) throws BaseException {
        // 해당 유저 닉네임 값 받기
        String nickname = member.getNickname();

        // post id로 있는지 찾고 값 받아오기. 없으면 에러.
        Post post = postRepository.findById(postId).orElseThrow(()
                -> BaseException.of(ErrorCode.POST_NOT_OWNER));

        // 받은 값으로 지오코딩
        String address = geoCodingService.getAddress(post.getLatitude(), post.getLongitude());

        // 좋아요 여부
        PostLike postLike = postLikeRepository.findByMemberIdAndPostId(member.getId(), postId);

        boolean liked = false;
        if (postLike != null)
            liked = true;

        // 받은 값들 dto에 넣기
        return new PostDetailResDto(postId, nickname, post.getEditContents(), post.getAdditionalContents(),
                post.getDevice(), post.getLatitude(), post.getLongitude(), address, post.getISO(), post.getShutterSpeed(),
                post.getFNumber(), post.getShootingDate(), post.getPhoto().getPhotoUrl(), liked);
    }
}
