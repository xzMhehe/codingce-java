package cn.com.codingce.testannotation;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/4 15:08
 */
@SuppressWarnings("all")
public class TestAnnotation {
    public static void main(String[] args) {

        /**
         * 1 用来充当注释的作用（仅仅是一个文字的说明） @Deprecated
         * 2 用来做代码的检测（验证） @Override
         * 3 可以携带一些信息（内容） （变量 数组 集合 对象  内存中） 文件.properties.xml  注解
         *
         */
        Date date = new Date(2000,1,1);
        date.getYear();

        ArrayList<String> arrayList = new ArrayList<>();

    }
}
