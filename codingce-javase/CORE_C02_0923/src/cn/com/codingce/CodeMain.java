package cn.com.codingce;

public class CodeMain {
    public static void main(String[] args) {
        /**
         * 创建一个整数一维数组，并初始化一些数据
         * 1）将所有是偶数的元素除以2，然后将数组输出到屏幕上。
         * 2）分别统计所有元素中个位数是2,5,7的元素个数，并输出到屏幕上，
         * 比如数组为{12,15,17,23,22,25}，则个位为2的元素个数为2，个位为5的元素个数为2，个位为7的元素个数为1.
         * 3）要求将程序代码和运行结果截图上传
         */

        int[] array = new int[]{12, 15, 17, 23, 22, 25};
        int i = 0, j = 0, k = 0;
        System.out.print("偶数的元素除以2: ");
        for(int a : array) {
            if(a % 2 == 0) {
                System.out.print(a / 2 + " ");
            }
            if (a % 10 == 2)
                i++;
            if (a % 10 == 5)
                j++;
            if (a % 10 == 7)
                k++;
        }
        System.out.println();
        System.out.println("个位为2的元素个数为" + i);
        System.out.println("个位为5的元素个数为" + j);
        System.out.println("个位为7的元素个数为" + k);
    }
}
