package cn.campsg.java.experiment.impl;

import cn.campsg.java.experiment.SeekJob;

import java.util.Arrays;

/**
 * @author xzMa
 */
public class BigDataSeeker implements SeekJob {

    private String name;
    private float[] scores;

    public BigDataSeeker() {
    }

    public BigDataSeeker(String name, float[] scores) {
        this.name = name;
        this.scores = scores;
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
    public String getName() {
        return this.name;
    }

    @Override
    public float getSeekerAverage() {
        return (this.scores[0] + this.scores[1])/2;
    }

    @Override
    public String toString() {
        return "BigDataSeeker{" +
                "name='" + name + '\'' +
                ", scores=" + Arrays.toString(scores) +
                '}';
    }
}
