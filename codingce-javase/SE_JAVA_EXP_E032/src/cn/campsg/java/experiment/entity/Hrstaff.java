package cn.campsg.java.experiment.entity;

public class Hrstaff extends Employe {

    public Hrstaff() {
    }

    public Hrstaff(String name) {
        super(name);
    }

    public Hrstaff(String name, int rank, int salary) {
        super(name, rank, salary);
    }

    public void paySalary(Employe employe) {
        if (employe.getLevel() > 1 && employe.getLevel() < 6) {
            employe.setSalary(employe.getSalary() + 500);
        } else if(employe.getLevel() < 9) {
            employe.setSalary(employe.getSalary() + 800);
        } else if(employe.getLevel() < 16) {
            employe.setSalary(employe.getSalary() + 1000);
        } else {
            System.out.println("不存在对应的职级，无法发放额外浮动薪水");
        }
    }
}
