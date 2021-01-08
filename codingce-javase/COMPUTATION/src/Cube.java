public class Cube implements Body {
    private float a;

    public Cube(float a) {
        this.a = a;
    }

    public Cube() {
    }

    @Override
    public float area() {
        return 6 * a * a;
    }
}
