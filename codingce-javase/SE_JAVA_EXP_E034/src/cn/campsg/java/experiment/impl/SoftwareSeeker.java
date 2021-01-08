package cn.campsg.java.experiment.impl;

import cn.campsg.java.experiment.SeekJob;

import java.util.Arrays;

/**
 * @author xzMa
 */
public class SoftwareSeeker implements SeekJob {

    private String name;
    private float[] scores;

    public SoftwareSeeker() {
    }

    public SoftwareSeeker(String name, float[] scores) {
        this.name = name;
        this.scores = scores;
    }

    @Override
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public float[] getScores() {
        return scores;
    }

    public void setScores(float[] scores) {
        this.scores = scores;
    }

    @Override
    public float getSeekerAverage() {
        return (float) ((this.scores[0] * 0.6) + (this.scores[1] * 0.4));
    }

    @Override
    public String toString() {
        return "SoftwareSeeker{" +
                "name='" + name + '\'' +
                ", scores=" + Arrays.toString(scores) +
                '}';
    }
}
