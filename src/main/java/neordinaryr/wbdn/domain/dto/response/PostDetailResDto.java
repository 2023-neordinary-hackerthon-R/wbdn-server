package neordinaryr.wbdn.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class PostDetailResDto {
    private Long postId;
    private String nickname;
    private String editContents;
    private String additionalContents;
    private String device;
    private Double latitude;
    private Double longitude;
    private String address;
    private String ISO;
    private String shutterSpeed;
    private String fNumber;
    private LocalDateTime shootingDate;
    private String photoUrl;
    private boolean like;
}
