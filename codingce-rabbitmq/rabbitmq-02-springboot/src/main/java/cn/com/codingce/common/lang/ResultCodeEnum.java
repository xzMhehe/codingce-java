package cn.com.codingce.common.lang;

/**
 * ResultCodeEnum
 *
 * @author ma
 */
public enum ResultCodeEnum implements ResultInterface {

    CALL_SUCCESS("9999"),
    CALL_FAILURE("0000");

    public final String resCode;

    ResultCodeEnum(String resCode) {
        this.resCode = resCode;
    }

    @Override
    public String Fmt() {
        return resCode;
    }

}
