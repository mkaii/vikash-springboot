public class AreaCalculator {
    public void getTriangleArea(int height, int base) {
        System.out.println("Triangle area is : " + 0.5 * base * height);
    }

    public void getSquareArea(int side) {
        System.out.println("Square area is : " + side * side);
    }

    public void getRectangleArea(int length, int base) {
        System.out.println("Rectangle area is : " +  length * base);
    }

    public void getCircleArea(int radius) {
        System.out.println("Circle area is : " + 3.14 * radius * radius);
    }
}
