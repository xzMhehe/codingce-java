public class Ball implements Body {
    private float r;

    public Ball() {
    }

    public Ball(float r) {
        this.r = r;
    }

    @Override
    public float area() {
        return (float) (4 * 3.14 * r * r);
    }
}
