import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    public static void main(String[] args) {
        Set<String> haSet = new HashSet<String>();
        haSet.add("hello");
        haSet.add("world");
        haSet.forEach(System.out::println);

        Set<String> trSet = new TreeSet<String>();
        trSet.add("hello");
        trSet.add("world");
        haSet.forEach(System.out::println);
    }

}
