import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        int a[] = {1, 3, 5, 7, 6, 4, 2};
        System.out.print("元素： ");
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();

        int b[] = Arrays.copyOf(a, 10);
        for (int i = 0; i < b.length; i++)
            System.out.print(b[i] + " ");
        System.out.println();

        int c[] = Arrays.copyOf(a, 5);
        for (int i = 0; i < c.length; i++)
            System.out.print(c[i] + " ");
        System.out.println();

        int d[] = Arrays.copyOfRange(a, 2, 5);
        for (int i = 0; i < d.length; i++)
            System.out.print(d[i] + " ");
        System.out.println();

        int e[] = new int[3];
        System.arraycopy(a, 2, e, 0, 3);
        for (int i = 0; i < e.length; i++)
            System.out.print(e[i] + " ");
        System.out.println();

    }

}
