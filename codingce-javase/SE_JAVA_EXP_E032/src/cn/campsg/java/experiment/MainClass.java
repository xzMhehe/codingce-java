package cn.campsg.java.experiment;

import cn.campsg.java.experiment.entity.Employe;
import cn.campsg.java.experiment.entity.Hrstaff;

public class MainClass {
    public static void main(String[] args) {
        Employe employe1 = new Employe("南帝", 1, 2344);
        Employe employe2 = new Employe("32", 14, 32323);
        Employe employe3 = new Employe("123", 13, 12331);
        Hrstaff hrstaff = new Hrstaff("HR", 5, 5000);
        employe1.work();
        employe2.work();
        employe3.work();
        hrstaff.work();
        hrstaff.paySalary(employe1);
        hrstaff.paySalary(employe2);
        hrstaff.paySalary(employe3);
        System.out.println(hrstaff.getName() + "开始发放工资");
        System.out.println("员工" + employe1.getName() + "的薪水:" + employe1.getSalary());
        System.out.println("员工" + employe2.getName() + "的薪水:" + employe2.getSalary());
        System.out.println("员工" + employe3.getName() + "的薪水:" + employe3.getSalary());
        hrstaff.paySalary(employe2);
        hrstaff.paySalary(employe3);
        hrstaff.paySalary(hrstaff);
        System.out.println("员工" + hrstaff.getName() + "的薪水:" + hrstaff.getSalary());
    }
}
