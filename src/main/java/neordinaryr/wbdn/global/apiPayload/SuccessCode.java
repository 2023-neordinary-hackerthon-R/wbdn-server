package neordinaryr.wbdn.global.apiPayload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessCode implements BaseCode {
    SUCCESS_OK(HttpStatus.OK, "OK", "요청 성공"),
    SUCCESS_CREATED(HttpStatus.CREATED, "CREATED", "요청 성공 및 리소스 생성됨"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
