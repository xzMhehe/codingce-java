package cn.com.codingce.mashibingthread;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 10:29
 */
public class Te implements Runnable {

    private int count = 10;

    @Override
    public synchronized void run() {
        count --;
        System.out.println(Thread.currentThread().getName() + "count:" + count);
    }

    public static void main(String[] args) {

        Te t = new Te();
        for (int i = 0; i<5;i++){
            new Thread(t,"THREAD" + i).start();
        }

    }

}
