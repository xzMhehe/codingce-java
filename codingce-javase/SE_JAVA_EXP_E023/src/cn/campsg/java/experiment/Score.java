package cn.campsg.java.experiment;

/**
 * @author xzMa
 */
public class Score {
    private float experiment;
    private float project;

    public Score() {
    }

    public Score(float experiment, float project) {
        this.experiment = experiment;
        this.project = project;
    }

    public float getExperiment() {
        return experiment;
    }

    public void setExperiment(float experiment) {
        this.experiment = experiment;
    }

    public float getProject() {
        return project;
    }

    public void setProject(float project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Score{" +
                "experiment=" + experiment +
                ", project=" + project +
                '}';
    }
}
