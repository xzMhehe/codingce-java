package cn.campsg.java.experiment;

import cn.campsg.java.experiment.entity.Employe;
import cn.campsg.java.experiment.entity.Hrstaff;

public class MainClass {
    public static void main(String[] args) {
        Employe employe1 = new Employe("123", 6, 1455);
        employe1.work();
        Hrstaff hrstaff = new Hrstaff("HR", 6, 8000);
        hrstaff.work();
        Company company = new Company();
        company.appraisals(hrstaff);
        company.appraisals(employe1);
    }
}
