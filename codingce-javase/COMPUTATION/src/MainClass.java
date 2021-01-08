public class MainClass {

    public static void main(String[] args) {
        Ball ball = new Ball(3);
        Cube cube = new Cube(6);
        System.out.println("ball的面积：" + getArea(ball));
        System.out.println("cube的面积：" + getArea(cube));
    }

    public static float getArea(Body b) {
        return b.area();
    }
}
