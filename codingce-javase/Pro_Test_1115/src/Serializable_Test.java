import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author
 */
public class Serializable_Test {

    public static void main(String[] args) {
        try {
            // 创建一个ObjectOutputStream输出流
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
            // 将对象序列化到文件object
            Person person = new Person("zhangsan", 18);
            // 直接将Object对象写入文件中
            oos.writeObject(person);
            System.out.println("Write an object into a file is successful.");
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // 创建一个 ObjectInputStream 输入流
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"));

            // 直接从文件中读取一个Object对象
            Person person = (Person) ois.readObject();
            System.out.println(person);
            System.out.println("Read an object from a file into memory is successful.");
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}