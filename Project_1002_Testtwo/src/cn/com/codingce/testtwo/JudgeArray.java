package cn.com.codingce.testtwo;

import java.util.Arrays;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/20 0:04
 */
public class JudgeArray {
    public static void main(String[] args) {
        String [] array1 = {"1","2","3"};
        String [] array2 = {"3","2"};
        Arrays.sort(array1);
        Arrays.sort(array2);
        for(String s:array1)
        {
            System.out.println(s);
        }
        for(String s:array2)
        {
            System.out.println(s);
        }
        if (Arrays.equals(array1, array2)) {
            System.out.println("两个数组中的元素值相同");
        } else {
            System.out.println("两个数组中的元素值不相同");
        }

    }

}
