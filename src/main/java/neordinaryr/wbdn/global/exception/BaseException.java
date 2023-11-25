package neordinaryr.wbdn.global.exception;

import lombok.Getter;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorCode code;

    public BaseException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public static BaseException of(ErrorCode code) {
        return new BaseException(code);
    }
}
