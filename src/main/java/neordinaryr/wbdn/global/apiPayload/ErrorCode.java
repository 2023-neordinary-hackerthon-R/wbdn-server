package neordinaryr.wbdn.global.apiPayload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode implements BaseCode {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "GLOBAL_001", ""),
    S3_OBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "S3_001", "S3에서 해당 파일을 찾지 못했습니다."),
    FILE_NOT_EXISTS(HttpStatus.BAD_REQUEST, "POST_001", "게시글 사진이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}