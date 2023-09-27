public class AreaCalculator {

    private Square square;
    private Triangle triangle;
    private Rectangle rectangle;
    private Circle circle;

    public void setSquare(Square square) {
        this.square = square;
    }

    public void setTriangle(Triangle triangle) {
        this.triangle = triangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public void getSquareArea() {
        System.out.println("Square area is : " + square.getSide() * square.getSide());
    }

    public void getTriangleArea() {
        System.out.println("Triangle area is : " + 0.5 * triangle.getHeight() * triangle.getBase());
    }

    public void getRectangleArea() {
        System.out.println("Rectangle area is : " +  rectangle.getBase() * rectangle.getLength());
    }

    public void getCircleArea() {
        System.out.println("Circle area is : " + 3.14 * circle.getRadius());
    }















}
