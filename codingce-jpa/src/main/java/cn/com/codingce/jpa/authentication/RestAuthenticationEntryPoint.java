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
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author 2460798168@qq.com
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private final Logger logger = LoggerFactory.getLogger(LoginAuthenticationFailureHandler.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        logger.error("Unauthorized", exception);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.setCharacterEncoding(Constants.UTF_8_VALUE);
        if (exception != null) {
            response.getWriter().write("身份验证失败");
        }
        if (exception instanceof UsernameNotFoundException || exception instanceof InternalAuthenticationServiceException) {
            response.getWriter().write("无法找到当前登录用户");
        }
        if (exception instanceof AccountStatusException) {
            response.getWriter().write("用户被禁止登录");
        }
        if (exception instanceof BadCredentialsException) {
            response.getWriter().write("密码错误");
        }
        response.getWriter().flush();
        response.getWriter().close();
    }
}
