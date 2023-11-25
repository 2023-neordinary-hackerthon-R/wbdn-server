package neordinaryr.wbdn.service;

import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.PostLike;
import neordinaryr.wbdn.domain.dto.response.PostLikeResDto;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.exception.BaseException;
import neordinaryr.wbdn.repository.PostLikeRepository;
import neordinaryr.wbdn.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeServcie {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    // 좋아요 수
    public Long likeCount(Long postId) throws BaseException {
        return postLikeRepository.countByPostId(postId);
    }

    // 좋아요 추가
    public PostLikeResDto saveLike(Long postId, Member member) {
        // 해당 유저가 해당 게시글 좋아요 눌렀는지 체크. 눌렀으면 에러로 있다고. 아니면 추가 후 post entity의 좋아요 수 + 1 해주기
        Post post = postRepository.findById(postId).orElseThrow(()
                -> BaseException.of(ErrorCode.POST_NOT_OWNER));

        PostLike postLike = postLikeRepository.findByMemberIdAndPostId(member.getId(), postId);

        if (postLike != null)
            throw BaseException.of(ErrorCode.ALREADY_LIKED);

        postLikeRepository.save(new PostLike(post, member));

        Long currentLike = post.getLikes();
        post.setLikes(currentLike + 1);

        return null;
    }
}
