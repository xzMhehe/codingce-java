package cn.campsg.java.experiment;

import java.util.ArrayList;

public class QueueCaller {
    private ArrayList<String> queue;

    public QueueCaller() {
        //构造函数里创建实例
        queue = new ArrayList<String>();
    }

    //获取容器中现有数量
    public int size() {
        return queue.size();
    }

    //向容器添加String类型的，此时第一个为就诊的，后面为等待就诊的
    public void fetchNumber(String patient) {
        queue.add(patient);
        System.out.println(patient + "前面还有" + (size() - 1) + "位在等候就诊");
    }

    //这里是查看候诊中的
    public void showPatients() {
        if (queue.size() <= 1) {
            return;
        } else {
            for (int i = 1; i <= queue.size() - 1; i++)
                System.out.println(queue.get(i) + "候诊中");
        }
    }

    //进行叫号，移除正在就诊的，然后进行呼叫下一个
    public void callNumber() {
        queue.remove(0);
        if (queue.size() != 0)
            System.out.println("请患者：" + queue.get(0) + "到诊室就诊！");
    }
}
