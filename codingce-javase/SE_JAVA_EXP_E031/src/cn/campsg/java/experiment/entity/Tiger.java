package cn.campsg.java.experiment.entity;

public class Tiger {

    private String type;

    public Tiger() {
    }

    public Tiger(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void eat() {
        String stuff;
        if (type == "华南虎") {
            stuff = "牛肉";
            System.out.println("饲养员喂" + type + "吃" + stuff);
        } else if (type == "新疆虎") {
            stuff = "羊肉";
            System.out.println("饲养员喂" + type + "吃" + stuff);
        } else if (type == "东北虎") {
            stuff = "猪肉";
            System.out.println("饲养员喂" + type + "吃" + stuff);
        }
    }
}
