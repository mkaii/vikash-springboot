public class AreaCalc {
/*    private Square square;
    private Triangle triangle;
    private Rectangle rectangle;
    private Circle circle; // creating object to access getters*/

    // we do not want to create objects of each class in Area calc

/*
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
*/  // if we create object of each class we have to create setters for each class also so we can send object here from main.
    // this will make the code bad and will burst

   /* public void triangleArea() {
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
    }*/ // we do not want Area calc to have the methods instead they should be in the respective classes and should overide the abstract method we have declared in the interface.

   // This is the best way!
    Shape shape ; // reference object of the interface

    public void setShape(Shape shape) {   // here we send the object from main based on the specific shape we create object from
        this.shape = shape;
    }

    // prints area based on the object we inject from main, will go in to respective class and find the right method.

    public void printShapeArea(){
        shape.printArea();
    }



}
