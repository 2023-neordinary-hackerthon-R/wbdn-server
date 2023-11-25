package neordinaryr.wbdn.converter;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.aws.s3.AmazonS3Manager;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Photo;
import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.dto.request.PostRequestDto;
import neordinaryr.wbdn.domain.dto.response.PostResponseDto;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.exception.BaseException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class PostConverter {

    private final AmazonS3Manager amazonS3Manager;

    private static AmazonS3Manager staticAmazonS3Manager;

    @PostConstruct
    public void init() {
        staticAmazonS3Manager = this.amazonS3Manager;
    }

    public static PostResponseDto.CreatePostDto toCreatePostDto(Post post) {
        return PostResponseDto.CreatePostDto.builder()
                                            .postId(post.getId())
                                            .build();
    }

    public static Post toPost(PostRequestDto.CreatePostDto request, Member member) throws IOException {
        return Post.builder()
                   .additionalContents(request.getAdditionalContents())
                   .editContents(request.getEditContents())
                   .ISO(request.getISO())
                   .fNumber(request.getFNumber())
                   .device(request.getDevice())
                   .latitude(request.getLatitude())
                   .longitude(request.getLongitude())
                   .shutterSpeed(request.getShutterSpeed())
                   .shootingDate(request.getShootingDate())
                   .member(member)
                   .build();
    }

    public static Photo toPhoto(Post post, MultipartFile postPhoto) throws IOException {
        Photo photo = Photo.builder()
                           .post(post)
                           .build();

        String fileUrl = null;
        if (postPhoto != null) {
            fileUrl = uploadPostPhoto(postPhoto);
        } else {
            throw BaseException.of(ErrorCode.FILE_NOT_EXISTS);
        }
        photo.setPhotoUrl(fileUrl);

        return photo;
    }

    public static String uploadPostPhoto(MultipartFile photo) throws IOException {
        String keyName = staticAmazonS3Manager.generatePostPhotoKeyName(photo.getOriginalFilename());
        String fileUrl = staticAmazonS3Manager.uploadFile(keyName, photo);

        return fileUrl;
    }
}
