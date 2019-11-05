package cn.com.codingce.jpa.common;


import java.nio.charset.Charset;
import java.util.regex.Pattern;

public class Constants {

    public static final String UTF_8_VALUE = "UTF-8";
    public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
    public static final String ROLE_LOGIN = "ROLE_LOGIN";
    public static final Charset UTF_8 = Charset.forName(UTF_8_VALUE);
    public static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-f]{32}");

}
