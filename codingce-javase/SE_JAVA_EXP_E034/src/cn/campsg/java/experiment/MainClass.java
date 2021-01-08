package cn.campsg.java.experiment;

import cn.campsg.java.experiment.impl.BigDataSeeker;
import cn.campsg.java.experiment.impl.SoftwareSeeker;

public class MainClass {
    public static void main(String[] args) {
        HrMarketer hrMarketer = new HrMarketer();
        float[] floats1 = new float[]{26.0f, 36.0f, 66.0f};
        float[] floats2 = new float[]{11.0f, 56.0f, 52.0f};
        float[] floats3 = new float[]{22.0f, 24.0f, 56.0f};
        float[] floats4 = new float[]{33.0f, 54.0f, 52.0f};
        SoftwareSeeker seeker1 = new SoftwareSeeker("111", floats1);
        SoftwareSeeker seeker2 = new SoftwareSeeker("1111", floats2);
        BigDataSeeker bigDataSeeker1 = new BigDataSeeker("222", floats3);
        BigDataSeeker bigDataSeeker2 = new BigDataSeeker("2222", floats4);
        System.out.println(hrMarketer.seekJob(seeker1));
        System.out.println(hrMarketer.seekJob(seeker2));
        System.out.println(hrMarketer.seekJob(bigDataSeeker1));
        System.out.println(hrMarketer.seekJob(bigDataSeeker2));
    }
}
