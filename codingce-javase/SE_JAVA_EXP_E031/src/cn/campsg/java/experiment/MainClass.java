package cn.campsg.java.experiment;

import cn.campsg.java.experiment.entity.Feeder;

public class MainClass {
    public static void main(String[] args) {
        Feeder s = new Feeder();
        s.feed();
        s.feed("华南虎");
    }
}
