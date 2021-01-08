package cn.com.codingce.testone;

public class Main {

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("cn.com.codingce.testone.Person");
            System.out.println(clazz.getModifiers());
            System.out.println(clazz.getName());
            System.out.println(clazz.getSimpleName());
            System.out.println(clazz.getPackage());
            System.out.println(clazz.getSuperclass());
            System.out.println();
            Person p = (Person) clazz.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
