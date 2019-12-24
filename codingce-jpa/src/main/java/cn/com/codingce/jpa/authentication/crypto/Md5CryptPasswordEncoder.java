package cn.com.codingce.jpa.authentication.crypto;

import cn.com.coding4fun.crypto.CryptoUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

/**
 * 自定义MD5密码加密策略
 */
public class Md5CryptPasswordEncoder implements PasswordEncoder {

    public static final Pattern MD5CRYPT_PATTERN = Pattern.compile("[a-fA-F0-9]{32}");

    @Override
    public String encode(CharSequence charSequence) {
        return CryptoUtils.encodeMD5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            return false;
        }
        if (!MD5CRYPT_PATTERN.matcher(encodedPassword).matches()) {
            return false;
        }
        return encodedPassword.equals(CryptoUtils.Md5Encrypt(rawPassword.toString()));
    }
}
