package testtwo;

public class MainClass {
    public static void main(String[] args) {
        Square square = new Square(5.2f);
        Rou rou = new Rou(6.3f);
        System.out.println(getPerimeter(square));
        System.out.println(getPerimeter(rou));
    }

    public static float getPerimeter(Shape s) {
        return s.perimeter();
    }
}
