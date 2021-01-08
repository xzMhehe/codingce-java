package cn.campsg.java.experiment;

/**
 * @author xzMa
 */
public class HrMarketer {

    String seekJob(SeekJob seekJob) {

        if (seekJob.getSeekerAverage() > SeekJob.SEEKER_AVERAGE_SCORE) {
            return seekJob.getName() + "被本公司录用";
        }

        return seekJob.getName() + "成绩未达标，不予录用";
    }
}
