package cn.campsg.java.experiment;

public class MainClass {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Employee[] employees = new Employee[3];
        employees[0] = new Employee("1001", "张一", "销售部", 5000.0f);
        employees[1] = new Employee("1002", "王二", "销售部", 6500.0f);
        employees[2] = new Employee("1001", "Alan", "研发部", 15000.0f);
        int count = 0;
        for (int i = 0; i < employees.length; i++) {
            if (isRepeat(i, employees))
                count++;
            System.out.println(employees[i].toString());
        }
        System.out.println("本公司有效工数:" + count);
    }

    public static boolean isRepeat(int index, Employee[] employees) {
        Employee emp = employees[index];
        for (int i = 0; i < employees.length; i++) {
            if (emp == employees[i])
                continue;
            if (emp.equals(employees[i]))
                return true;
        }
        return false;
    }
}
