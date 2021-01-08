package cn.campsg.java.experiment.entity;

public class Employe {

    private String name;
    private int level;
    private int salary;

    public Employe() {
    }

    public Employe(String name) {
        this.name = name;
    }

    public Employe(String name, int level, int salary) {
        this.name = name;
        this.level = level;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void work() {
        System.out.println(this.name + "按领导的要求完成任务！");
    }

}
