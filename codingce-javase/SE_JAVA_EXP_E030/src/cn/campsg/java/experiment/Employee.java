package cn.campsg.java.experiment;

public class Employee {
    private String no = null;
    private String name = null;
    private String department = null;
    private Float salary = null;

    public Employee(String no, String name, String departmrnt, Float salary) {
        this.no = no;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;

    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof Employee))
            return false;
        Employee emp = (Employee) o;
        if (!emp.getNo().equals(this.getNo()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("工号:").append(this.getNo()).append(",");
        buffer.append("姓名：").append(this.getName()).append(",");
        buffer.append("部门:").append(this.getDepartment()).append(",");
        buffer.append("薪水:").append(this.getSalary());
        return buffer.toString();
    }
}
