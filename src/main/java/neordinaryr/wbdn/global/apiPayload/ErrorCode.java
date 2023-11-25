package neordinaryr.wbdn.global.apiPayload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode implements BaseCode {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "GLOBAL_001", ""),

    //로그인 Global Error Code
    SUCCESS_OK(HttpStatus.OK, "SUCCESS", "요청 성공"),
    SUCCESS_CREATED(HttpStatus.CREATED, "SUCCESS", "요청 성공 및 리소스 생성됨"),
    SUCCESS_ACCEPTED(HttpStatus.ACCEPTED, "SUCCESS", "요청 성공"),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "JWT-001", "JWT 인증 실패"),
    INVALID_USER_JWT(HttpStatus.FORBIDDEN, "JWT-002", "권한이 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "JWT-003", "자격 증명이 이루어지지 않았습니다"),

    //로그인 User Error Code
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "User-001", "이메일 형식이 올바르지 않습니다."),
    INVALID_PASSWORD_PATTERN(HttpStatus.BAD_REQUEST, "User-002", "비밀번호 형식이 올바르지 않습니다."),
    DUPLICATE_LOGIN_ID(HttpStatus.CONFLICT, "User-003", "아이디  중복"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "User-004", "유저가 존재하지 않습니다."),

    //토큰 Authh Error Code
    AUTH_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH-001", "토큰 만료"),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_002", "토큰이 유효하지 않습니다."),
    INVALID_LOGIN_REQUEST(HttpStatus.UNAUTHORIZED, "AUTH-003", "올바른 이메일이나 패스워드가 아닙니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL ERROR", "서버 에러, 서버 개발자에게 문의하세요."),

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
    POST_NOT_OWNER(HttpStatus.FORBIDDEN, "POST_003", "게시글 소유자가 아닙니다."),
    ALREADY_LIKED(HttpStatus.CONFLICT, "POST_004", "이미 좋아요를 누른 게시글 입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
