package cn.campsg.java.experiment.entity;

/**
 * @author xzMa
 */
public class Bank {

    private long balance = 1000L;

    public Bank(long balance) {
        this.balance = balance;
    }

    public Bank() {
    }

    public void withDraw(long cash) throws InterruptedException {
        if (cash > balance) {
            throw new InterruptedException("余额不足");
        }
        System.out.println("您的取款额为：" + cash + "；账户余额为：" + balance);
        balance = balance - cash;
    }
}
