package cn.com.codingce.jpa.authentication;

import cn.com.codingce.jpa.authentication.core.UserInfoDetails;
import cn.com.codingce.jpa.authentication.jwt.JwtAuthenticationTokenUtils;
import cn.com.codingce.jpa.common.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Rest授权成功
 */
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding(Constants.UTF_8_VALUE);
        UserInfoDetails userDetails = (UserInfoDetails) authentication.getPrincipal();
        String authenticationToken = JwtAuthenticationTokenUtils.getAuthenticationToken(userDetails.getUsername(), userDetails.getId(), userDetails.getOrgId());
        ObjectMapper mapper = new ObjectMapper();
        LoginResult result = new LoginResult(userDetails.getUsername(), userDetails.getRealName(), userDetails.getEmployeeNo(), userDetails.getOrgName(),
                new LoginResult.AccessToken(authenticationToken, LocalDateTime.now().plusMinutes(30L).toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        response.getWriter().write(mapper.writeValueAsString(result));
    }
}

class LoginResult implements Serializable {
    private String loginName;
    private String realName;
    private String employeeNo;
    private String orgName;
    private AccessToken token;

    public LoginResult(String loginName, String realName, String employeeNo, String orgName, AccessToken token) {
        this.loginName = loginName;
        this.realName = realName;
        this.employeeNo = employeeNo;
        this.orgName = orgName;
        this.token = token;
    }

    static class AccessToken implements Serializable {
        private String token;
        private Long expiresTime;

        public AccessToken(String token, Long expiresTime) {
            this.token = token;
            this.expiresTime = expiresTime;
        }

        public String getToken() {
            return token;
        }

        public Long getExpiresTime() {
            return expiresTime;
        }

        @Override
        public String toString() {
            return "AccessToken{" +
                    "token='" + token + '\'' +
                    ", expiresTime=" + expiresTime +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "loginName='" + loginName + '\'' +
                ", realName='" + realName + '\'' +
                ", employeeNo='" + employeeNo + '\'' +
                ", orgName='" + orgName + '\'' +
                ", token=" + token +
                '}';
    }

    public String getLoginName() {
        return loginName;
    }

    public String getRealName() {
        return realName;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public AccessToken getToken() {
        return token;
    }
}