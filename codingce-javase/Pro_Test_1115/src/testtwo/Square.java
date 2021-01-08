package testtwo;

public class Square implements Shape {
    float a;

    public Square() {
    }

    public Square(float a) {
        this.a = a;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    @Override
    public float perimeter() {
        return 4 * a;
    }
}
