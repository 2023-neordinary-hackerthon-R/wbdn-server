package neordinaryr.wbdn.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;

public class PostRequestDto {

    @Getter
    public static class CreatePostDto {

        @NotNull(message = "기기는 필수입니다.")
        private String device;

        private String ISO;

        private String shutterSpeed;

        private String fNumber;

        private String editContents;

        private String additionalContents;

        private Double latitude;

        private Double longitude;

        private LocalDateTime shootingDate;
    }
}
