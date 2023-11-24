import java.io.PrintWriter;

public class AreaCalc {
    private Square square;
    private Triangle triangle;
    private Rectangle rectangle;
    private Circle circle; // creating object to access getters

    public void setSquare(Square square) {  // setting square value based on main class from where we send the object
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

    public void triangleArea() {
        System.out.println("triangle area is : " + 0.5 * triangle.getBase() * triangle.getHeight());
    }
    // creating methods that will calculate object values sent from main


    public void squareArea() {
        System.out.println("square area is : " + square.getSide() * square.getSide());
    }


    public void rectangleArea() {
        System.out.println("rectangle area is : " + rectangle.getLenght() * rectangle.getBase());
    }

    public void circleArea() {
        System.out.println("circle area is : " + circle.getRadius() * circle.getRadius());
    }

}
