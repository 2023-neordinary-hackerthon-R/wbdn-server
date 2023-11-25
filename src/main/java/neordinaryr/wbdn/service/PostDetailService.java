package neordinaryr.wbdn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.dto.response.PostDetailResDto;
import neordinaryr.wbdn.domain.dto.response.PostListDto;
import neordinaryr.wbdn.global.exception.BaseException;
import neordinaryr.wbdn.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostDetailService {

    private PostRepository postRepository;
    private PostLikeServcie postLikeServcie;

    // 전체 조회
    public PostListDto.PostListResDto findPostAll() throws BaseException {
        // 해당 유저 닉네임 값 받기
        //Member member =
        List<Post> findPosts = postRepository.findAll();

        // post 리스트 값 받기
//        if (findPosts == null || findPosts.isEmpty())
//            throw new BaseException()
        List<PostListDto.GetPostListDto> result = findPosts.stream()
                                                           .map(p -> {
                                                               Long likes = postLikeServcie.likeCount(p.getId());
                                                               return new PostListDto.GetPostListDto(p.getId(), p.getMember().getNickname(),
                                                                   p.getPhoto().getPhotoUrl(), likes);
                                                           })
                                                           .collect(Collectors.toList());

        return new PostListDto.PostListResDto(null, null, result);
    }

    // 해당 Post 상세 조회
    public PostDetailResDto findPostDetail(Long postId) throws BaseException {
        // 해당 유저 닉네임 값 받기

        // post id로 있는지 찾고 값 받아오기. 없으면 에러.
        Optional<Post> post = postRepository.findById(postId);
//        if(post.isEmpty())

        // 받은 값으로 지오코딩

        // 받은 값들 dto에 넣기
        return null;
    }
}
