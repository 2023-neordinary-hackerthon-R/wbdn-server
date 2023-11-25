package neordinaryr.wbdn.global.apiPayload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode implements BaseCode {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "GLOBAL_001", "");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}