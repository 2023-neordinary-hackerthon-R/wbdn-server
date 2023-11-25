package neordinaryr.wbdn.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.exception.BaseException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        throw BaseException.of(ErrorCode.INVALID_JWT);
    }
}