package cn.com.codingce.jpa.authentication.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public final class JwtAuthenticationTokenUtils {

    /**
     * 定义令牌密钥
     */
    public static final String SECRET = "#KrzQ!XYo%LVm1Igi7SUdh!8Xss7rz63BCyJlDZsq!1WFe0qqJJvsl%4Rt4DWgke";

    /**
     * 定义Json Web Token 过期时间，单位：分钟
     */
    public static final Long EXPIRATION_TIME_MINUTES_IN_THE_FUTURE = 1000L * 60L * 240L;

    /**
     * 谁创建并签发令牌
     */
    public static final String ISSUER = "Coding4fun";

    /**
     * 定义谁将使用令牌
     */
    public static final String AUDIENCE = "BrowserUser";

    /**
     * 定义令牌主要用于那个方面
     */
    public static final String SUBJECT = "Authentication";

    /**
     * 定义令牌中自定义字段
     */
    public static final String USER_ID = "USER_ID";

    /**
     * 定义令牌中自定义字段
     */
    public static final String LOGIN_NAME = "LOGIN_NAME";

    /**
     * 定义令牌中自定义字段
     */
    public static final String ORG_ID = "ORG_ID";

    /**
     * 获取授权Token
     *
     * @param loginName
     * @return
     */
    public static String getAuthenticationToken(String loginName, String userId, String orgId) throws UnsupportedEncodingException {
        Date expiresAt = new Date(System.currentTimeMillis() + EXPIRATION_TIME_MINUTES_IN_THE_FUTURE);
        Date issuedAt = new Date();
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withAudience(AUDIENCE)
                .withExpiresAt(expiresAt)
                .withIssuedAt(issuedAt)
                .withClaim(LOGIN_NAME, loginName)
                .withClaim(USER_ID, userId)
                .withClaim(ORG_ID, orgId)
                .sign(algorithm);
    }

    /**
     * 检查令牌是否有效
     *
     * @param authenticationToken
     * @return
     */
    public static boolean checkAuthenticationToken(String authenticationToken) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build(); //Reusable verifier instance
        verifier.verify(authenticationToken);
        return true;
    }

    public static String getLoginNameFromToken(String authenticationToken) {
        return JWT.decode(authenticationToken).getClaim(LOGIN_NAME).asString();
    }

    public static String getUserIDFromToken(String authenticationToken) {
        return JWT.decode(authenticationToken).getClaim(USER_ID).asString();
    }

    public static String getOrgIDFromToken(String authenticationToken) {
        return JWT.decode(authenticationToken).getClaim(ORG_ID).asString();
    }
}
