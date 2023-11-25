package neordinaryr.wbdn.global.apiPayload;

import org.springframework.http.HttpStatus;

public interface BaseCode {
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();

}
