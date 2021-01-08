import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xzMa
 */
public class SchoolManager {

    private ArrayList<HashMap<String, Student>> list = new ArrayList<>();

    public void saveClass(HashMap<String, Student> map) {
        list.add(map);
    }

    public String getAllStudentByClass(int index) {
        String str = "班级" + index + "有学生:\n";
        HashMap<String, Student> map = list.get(index);
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String num = it.next();
            Student student = map.get(num);
            str += "学号：" + num + "，" + student + "\n";
        }
        return str;
    }


    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        HashMap<String, Student> students1 = new HashMap<>();
        HashMap<String, Student> students2 = new HashMap<>();

        Student student1 = new Student("张三", 25, true);
        Student student2 = new Student("李四", 34, false);

        students1.put("123456", student1);
        students2.put("987654", student2);

        schoolManager.saveClass(students1);
        schoolManager.saveClass(students2);

        System.out.println(schoolManager.getAllStudentByClass(1));
    }
}
