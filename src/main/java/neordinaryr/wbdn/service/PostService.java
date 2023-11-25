package neordinaryr.wbdn.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.PostConverter;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.dto.request.PostRequestDto;
import neordinaryr.wbdn.repository.PhotoRepository;
import neordinaryr.wbdn.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PhotoRepository photoRepository;

    public Post createPost(PostRequestDto.CreatePostDto request, MultipartFile photo, Member author) throws IOException {
        Post post = postRepository.save(PostConverter.toPost(request, author));
        photoRepository.save(PostConverter.toPhoto(post, photo));

        return post;
    }
}
