import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author xzMa
 */
public class ClassManager {

    Map<String, Student> map = new HashMap<>();

    public void saveStudent(String num, Student student) {
        map.put(num, student);
    }

    public String checkAllStudent() {
        String str = "";
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String num = it.next();
            str += "学号：" + num + "，" + map.get(num) + "\n";
        }
        return str;
    }

    public static void main(String[] args) {
        Student student1 = new Student("张三", 16, true);
        Student student2 = new Student("李四", 16, false);
        ClassManager classManager = new ClassManager();
        classManager.saveStudent("11559", student1);
        classManager.saveStudent("335577", student2);
        System.out.print(classManager.checkAllStudent());
    }
}
