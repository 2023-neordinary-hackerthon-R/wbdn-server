package neordinaryr.wbdn.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PostLikeResDto {
    private Long memberId;
    private Long postId;
}
