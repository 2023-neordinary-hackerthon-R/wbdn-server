package neordinaryr.wbdn.global.apiPayload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode implements BaseCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL ERROR", "서버 에러, 서버 개발자에게 문의하세요."),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "GLOBAL_001", "검증에 실패했습니다. result를 확인하세요."),

    // Comment 관련
    NO_SUCH_COMMENT_ERROR(HttpStatus.BAD_REQUEST, "COMMENT_001", "해당하는 ID를 가진 댓글이 없습니다."),
    COMMENT_MEMBER_MISMATCH_ERROR(HttpStatus.NOT_ACCEPTABLE, "COMMENT_002", "지우려는 사용자의 ID와 댓글의 ID가 동일하지 않습니다."),

    // Reply 관련
    NO_SUCH_REPLY_ERROR(HttpStatus.BAD_REQUEST, "REPLY_001", "해당하는 ID를 가진 답글이 없습니다."),
    REPLY_MEMBER_MISMATCH_ERROR(HttpStatus.NOT_ACCEPTABLE, "REPLY_002", "지우려는 사용자의 ID와 답글의 ID가 동일하지 않습니다."),

    // Global

    // S3 관련
    S3_OBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "S3_001", "S3에서 해당 파일을 찾지 못했습니다."),

    // POST 관련
    FILE_NOT_EXISTS(HttpStatus.BAD_REQUEST, "POST_001", "게시글 사진이 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_002", "해당 게시글이 존재하지 않습니다."),
    POST_NOT_OWNER(HttpStatus.FORBIDDEN, "POST_003", "게시글 소유자가 아닙니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
