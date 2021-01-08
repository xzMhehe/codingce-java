package cn.campsg.java.experiment;

import cn.campsg.java.experiment.exception.RoseException;

/**
 * @author xzMa
 */
public class Rose {
    private int total;

    public Rose(int total) {
        this.total = total;
    }

    public Rose() {
    }

    public void giveRose(int num) throws RoseException {
        if (num < 1) {
            System.out.println("送花的数量不正确：数量不能小于1！");
        } else if (total <= 0 || num > total) {
            throw new RoseException("没钱买" + num + "支玫瑰花");
        }

        System.out.println("亲爱的，送你" + num + "朵玫瑰花！");
        this.total -= num;
    }
}
