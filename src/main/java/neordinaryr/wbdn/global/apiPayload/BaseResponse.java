package neordinaryr.wbdn.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> {

    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(Include.NON_NULL)
    private final T result;

    public static <T> BaseResponse<T> onSuccess(T result) {
        return new BaseResponse<>(true, SuccessCode.SUCCESS_OK.getCode(), SuccessCode.SUCCESS_OK.getMessage(), result);
    }

    public static <T> BaseResponse<T> onSuccess(BaseCode code, T result) {
        return new BaseResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    public static <T> BaseResponse<T> onFailure(BaseCode code) {
        return new BaseResponse<>(false, code.getCode(), code.getMessage(), null);
    }

    public static <T> BaseResponse<T> onFailure(BaseCode code, T result) {
        return new BaseResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
