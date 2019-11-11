package cn.com.codingce.entity;

/**
 * 高度分层的结构
 * @author 2460798168@qq.com
 * @date 2019/11/11 13:08
 */
public class Outer {
    Nested nested;

    public Nested getNested() {
        return nested;
    }

    public void setNested(Nested nested) {
        this.nested = nested;
    }
}
