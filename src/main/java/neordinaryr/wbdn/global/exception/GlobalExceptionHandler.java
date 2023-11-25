package neordinaryr.wbdn.global.exception;

import java.util.List;
import neordinaryr.wbdn.global.apiPayload.BaseResponse;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Object>> baseException(BaseException e) {
        ErrorCode code = e.getCode();
        return convertToResponse(code);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<String>> methodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult()
                                        .getFieldErrors();
        return convertToResponse(ErrorCode.VALIDATION_ERROR,
            extractFieldErrorMessage(fieldErrors));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<BaseResponse<String>> bindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult()
                                        .getFieldErrors();
        return convertToResponse(ErrorCode.VALIDATION_ERROR,
            extractFieldErrorMessage(fieldErrors));
    }

    private String extractFieldErrorMessage(List<FieldError> fieldErrors) {
        if (fieldErrors.size() == 1) {
            return fieldErrors.get(0)
                              .getDefaultMessage();
        }

        StringBuilder buffer = new StringBuilder();
        for (FieldError error : fieldErrors) {
            buffer.append(error.getDefaultMessage())
                  .append("\n");
        }
        return buffer.toString();
    }

    private ResponseEntity<BaseResponse<Object>> convertToResponse(ErrorCode code) {
        return ResponseEntity
            .status(code.getHttpStatus())
            .body(BaseResponse.onFailure(code));
    }

    private ResponseEntity<BaseResponse<String>> convertToResponse(ErrorCode code, String message) {
        return ResponseEntity
            .status(code.getHttpStatus())
            .body(BaseResponse.onFailure(code, message));
    }
}