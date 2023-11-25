package neordinaryr.wbdn.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.exception.BaseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomMemberDetailsService memberDetailsService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (jwtTokenProvider.isTokenValid(token)) {
                Long memberId = jwtTokenProvider.getId(token);
                UserDetails memberDetails = memberDetailsService.loadUserByUsername(memberId.toString());

                if (memberDetails != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(memberDetails, "", memberDetails.getAuthorities());

                    SecurityContextHolder.getContext()
                                         .setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    throw BaseException.of(ErrorCode.MEMBER_NOT_FOUND);
                }
            } else {
                throw BaseException.of(ErrorCode.INVALID_JWT);
            }
        }
        filterChain.doFilter(request, response);
    }
}