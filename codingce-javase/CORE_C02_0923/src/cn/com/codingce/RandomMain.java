package cn.com.codingce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xzMa
 */
public class RandomMain {
    public static void main(String[] args) {
        List givenList = getList();
        // 随机打乱原来的顺序, 就像洗牌一样
        Collections.shuffle(givenList);
        // 设置生成随机数的个数   可自己设置
        int randomSeriesLength = 2;
        for(Object str : givenList) {
            System.out.println(str);
        }
        // 取得的是打乱顺序集合下标为0到2的元素
        List<String> randomSeries = givenList.subList(0, randomSeriesLength);
        System.out.println("幸运用户为: " + randomSeries);
    }

    public static List<String> getList() {
        List list = new ArrayList<>();
        list.add("1950413523");
        list.add("1950613533");
        list.add("1950313508");
        return list;
    }
}
