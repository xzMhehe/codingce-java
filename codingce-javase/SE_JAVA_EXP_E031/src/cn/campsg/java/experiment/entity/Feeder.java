package cn.campsg.java.experiment.entity;

public class Feeder {
    private Tiger[] tigers;

    public Feeder() {

        tigers = new Tiger[3];
        tigers[0] = new Tiger("华南虎");
        tigers[1] = new Tiger("东北虎");
        tigers[2] = new Tiger("新疆虎");
    }

    public void feed(String type) {
        if (type != null) {
            for (int i = 0; i < tigers.length; i++) {
                if (tigers[i].getType().equals(type)) {
                	// 注意使用equals，不要用==，前者判断内容，后者判断地址
                    tigers[i].eat();
                }
            }
        } else {
            System.out.println("饲养员喂华南虎吃牛肉");
            System.out.println("饲养员喂东北虎吃猪肉");
            System.out.println("饲养员喂新疆虎吃羊肉");
        }
    }

    public void feed() {
        feed(null);
    }
}
