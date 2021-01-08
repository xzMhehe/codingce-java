package cn.campsg.java.experiment.entity;

public class PopSinger extends AbstractSinger {
    @Override
    public void introduce() {
        System.out.println("Hello，我是歌手。");
    }
    @Override
    public void sing() {
        System.out.println("我是唱流行乐的。");
    }
}
