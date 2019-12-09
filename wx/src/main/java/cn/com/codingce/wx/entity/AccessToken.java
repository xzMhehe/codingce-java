package cn.com.codingce.wx.entity;

/**
 * @author 2460798168@qq.com
 * @date 2019/12/9 9:34
 */
public class AccessToken {
    /**
     * accessToken  Token
     */
    private String accessToken;
    /**
     * expireTime   过期时间
     */
    private long expireTime;

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessToken(String accessToken, long expireTime) {
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }

    public AccessToken(String accessToken, String expiresIn) {
        this.accessToken = accessToken;
        expireTime = System.currentTimeMillis() + Integer.parseInt(expiresIn) * 1000;
    }

    public AccessToken() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }

    /**
     * 判断token是否过期
     * @return
     */
    public boolean isExpired() {
        return System.currentTimeMillis() > expireTime;
    }
}
