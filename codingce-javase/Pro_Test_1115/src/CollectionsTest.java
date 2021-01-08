import java.util.ArrayList;
import java.util.Collections;

public class CollectionsTest {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(-1);
        a.add(-5);
        a.add(10);
        a.add(5);
        a.add(12);
        a.add(43);
        a.add(3);
        a.add(7);
        System.out.println("原序列：" + a);
        System.out.println("最大值为：" + Collections.max(a));
        System.out.println("最小值为：" + Collections.min(a));
        Collections.replaceAll(a, -1, -7);
        System.out.println("替换后：" + a);
        System.out.println("3出现的频率：" + Collections.frequency(a, 3));
        Collections.sort(a);
        System.out.println("排序后：" + a);
        System.out.println("二分查找：" + Collections.binarySearch(a, 5));

    }
}
