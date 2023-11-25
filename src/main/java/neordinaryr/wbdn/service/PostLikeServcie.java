package neordinaryr.wbdn.service;

import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.domain.dto.response.PostLikeResDto;
import neordinaryr.wbdn.global.exception.BaseException;
import neordinaryr.wbdn.repository.PostLikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeServcie {

    private PostLikeRepository postListRepository;

    // 좋아요 수
    public Long likeCount(Long postId) throws BaseException {
        return postListRepository.countByPostId(postId);
    }

    // 좋아요 추가
    public PostLikeResDto saveLike( ) {
        // 해당 유저가 해당 게시글 좋아요 눌렀는지 체크. 눌렀으면 에러로 있다고. 아니면 추가 후 post entity의 좋아요 수 + 1 해주기

        return null;
    }
}
