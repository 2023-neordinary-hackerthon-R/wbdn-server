package neordinaryr.wbdn.service;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.aws.s3.AmazonS3Manager;
import neordinaryr.wbdn.converter.PostConverter;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.dto.request.PostRequestDto;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.exception.BaseException;
import neordinaryr.wbdn.repository.PhotoRepository;
import neordinaryr.wbdn.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PhotoRepository photoRepository;
    private final AmazonS3Manager amazonS3Manager;

    @Transactional
    public Post createPost(PostRequestDto.CreatePostDto request, MultipartFile photo, Member author) throws IOException {
        Post post = postRepository.save(PostConverter.toPost(request, author));
        photoRepository.save(PostConverter.toPhoto(post, photo));

        return post;
    }

    @Transactional
    public void deletePost(Long postId, Member member) {
        Post post = postRepository.findById(postId).orElseThrow(() -> BaseException.of(ErrorCode.POST_NOT_FOUND));

        if (!post.getMember().equals(member)) {
            throw BaseException.of(ErrorCode.POST_NOT_OWNER);
        }

        String keyName = amazonS3Manager.getKeyName(post.getPhoto().getPhotoUrl());
        amazonS3Manager.deleteFile(keyName);

        postRepository.delete(post);
    }

//    public List<PostListMapDto> getPostsOnMap(Double currentLat, Double currentLon, Double upperRightLat, Double upperRightLon) {
//        return postRepository.findByLocation(currentLat, currentLon, upperRightLat, upperRightLon);
//    }

    public List<Post> getPostsOnMapTemp() {
        return postRepository.findAll();
    }
}
