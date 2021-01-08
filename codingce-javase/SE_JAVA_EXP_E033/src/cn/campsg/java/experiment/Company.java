package cn.campsg.java.experiment;

import cn.campsg.java.experiment.entity.Employe;
import cn.campsg.java.experiment.entity.Hrstaff;

public class Company {
    public void appraisals(Employe emp) {
        emp.work();
        if (emp instanceof Hrstaff) {
            System.out.println("HR的雇员等级为:"+ emp.getLevel() + "," + ((Hrstaff) emp).looupSalary(emp.getLevel()));
        }
    }
}
