package cn.com.codingce.jpa.authentication;

import cn.com.codingce.jpa.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Rest授权失败
 */
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final Logger logger = LoggerFactory.getLogger(LoginAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        logger.error("Unauthorized", exception);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.setCharacterEncoding(Constants.UTF_8_VALUE);
        if (exception instanceof UsernameNotFoundException) {
            response.getWriter().write("用户名或密码错误");
        } else if (exception instanceof InternalAuthenticationServiceException) {
            response.getWriter().write(exception.getMessage());
        } else if (exception instanceof AccountStatusException) {
            response.getWriter().write("用户已被禁用");
        } else if (exception instanceof BadCredentialsException) {
            response.getWriter().write("用户名或密码错误");
        } else {
            response.getWriter().write("系统内部错误，请刷新重试");
        }
        response.getWriter().flush();
        response.getWriter().close();
    }
}
