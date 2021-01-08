import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
    public static void main(String[] args) {
        Map<String, String> hMap = new HashMap<String, String>();
        hMap.put("1", "哈哈");
        hMap.put("2", "呵呵");

        Set<String> set = hMap.keySet();
        Iterator<String> it = set.iterator();

        while (it.hasNext()) {
            String num = it.next();
            System.out.println("编号" + num + ", " + hMap.get(num));
        }
    }

}
