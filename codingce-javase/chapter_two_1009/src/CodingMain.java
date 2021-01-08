/**
 * 在JDK1.5之前,switch循环只支持byte short char int四种数据类型.
 * JDK1.5 在switch循环中增加了枚举类与byte short char int的包装类,对四个包装类的支持是因为java编译器在底层手动进行拆箱,而对枚举类的支持是因为枚举类有一个ordinal方法,该方法实际上是一个int类型的数值.
 * jdk1.7开始支持String类型,但实际上String类型有一个hashCode算法,结果也是int类型.而byte short char类型可以在不损失精度的情况下向上转型成int类型.所以总的来说,可以认为switch中只支持int.
 * @author xzMa
 */
public class CodingMain {
    public static void main(String[] args) {
        int count = switchCase(2);
        System.out.println("count="+count);

    }
    public static int switchCase(int number){
        int sum = 0;
        switch(number){
            case 1:
                sum = sum + number;
            case 2:
                sum = sum + number*2;
            case 3:
                sum = sum + number*3;
            case 4:
                sum = sum + number*4;
        }
        return sum;
    }
    public static void testTwo(int x, int y) {
        if (x < 0) {
            y = -1;
        } else if (x == 0) {
            y = 0;
        } else {
            y = 1;
        }
    }

    public static void testThree(int x, int y) {
        switch(x > 0 ? 1 : 0)
        {
            case 1:
                System.out.println("1");
                break;
            case 0:
                switch(x == 0 ? 0 : 1) {
                    case 1:
                        System.out.println("0");
                        break;
                    case 0:
                        System.out.println("-1");
                        ;
                        break;
                }
        }
    }

}
