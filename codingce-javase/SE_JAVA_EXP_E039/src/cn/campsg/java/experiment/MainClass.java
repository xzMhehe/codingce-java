package cn.campsg.java.experiment;

public class MainClass {
    public static void main(String[] args) {
        // 创建默认叫号排队器
        QueueCaller qc = new QueueCaller();
        // 取3个号
        for (int i = 1; i <= 3; i++) {
            qc.fetchNumber("张山-" + i);
        }

        while (qc.size() != 0) {
            qc.callNumber();
            // 显示候诊信息
            qc.showPatients();
        }
    }
}
