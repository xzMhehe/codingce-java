package testtwo;

public class Rou implements Shape {
    float r;

    public Rou(float r) {
        this.r = r;
    }

    public Rou() {
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    @Override
    public float perimeter() {
        return (float) (2 * 3.14 * r);
    }
}
