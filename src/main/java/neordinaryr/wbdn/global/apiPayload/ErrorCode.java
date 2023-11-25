package neordinaryr.wbdn.global.apiPayload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode implements BaseCode {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "GLOBAL_001", ""),

    // Comment 관련
    NO_SUCH_COMMENT_ERROR(HttpStatus.BAD_REQUEST, "COMMENT_001", "해당하는 ID를 가진 댓글이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
