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
    NO_SUCH_COMMENT_ERROR(HttpStatus.BAD_REQUEST, "COMMENT_001", "해당하는 ID를 가진 댓글이 없습니다."),
    COMMENT_MEMBER_MISMATCH_ERROR(HttpStatus.NOT_ACCEPTABLE, "COMMENT_002", "지우려는 사용자의 ID와 댓글의 ID가 동일하지 않습니다."),

    // Reply 관련
    NO_SUCH_REPLY_ERROR(HttpStatus.BAD_REQUEST, "REPLY_001", "해당하는 ID를 가진 답글이 없습니다."),
    REPLY_MEMBER_MISMATCH_ERROR(HttpStatus.NOT_ACCEPTABLE, "REPLY_002", "지우려는 사용자의 ID와 답글의 ID가 동일하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
